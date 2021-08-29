package de.di.barcodeserver.elo;

import com.lowagie.text.pdf.PdfReader;
import de.di.dokinform.elo.ELOClientNG;
import de.elo.ix.client.CheckoutUsersC;
import de.elo.ix.client.DocMask;
import de.elo.ix.client.DocMaskC;
import de.elo.ix.client.EditInfo;
import de.elo.ix.client.EditInfoC;
import de.elo.ix.client.FindResult;
import de.elo.ix.client.FindTasksInfo;
import de.elo.ix.client.FindWorkflowInfo;
import de.elo.ix.client.IXConnFactory;
import de.elo.ix.client.IXConnection;
import de.elo.ix.client.LockC;
import de.elo.ix.client.ObjKey;
import de.elo.ix.client.Sord;
import de.elo.ix.client.SordC;
import de.elo.ix.client.UserInfo;
import de.elo.ix.client.UserTask;
import de.elo.ix.client.WFCollectNode;
import de.elo.ix.client.WFDiagram;
import de.elo.ix.client.WFDiagramC;
import de.elo.ix.client.WFEditNode;
import de.elo.ix.client.WFNode;
import de.elo.ix.client.WFTakeNodeC;
import de.elo.ix.client.WFTypeC;
import java.io.File;
import java.io.IOException;
import de.elo.utils.net.RemoteException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 * A client for ELO providing methods to access the data in the ELO archive.
 *
 * @author A. Sopicki
 */
public class ELOClient extends de.di.dokinform.elo.ELOClientNG {
    
   

    private static final Logger logger = Logger.getLogger(ELOClient.class);
    private static final String PILLCROW = "¶";
    private String comment = null;

    public ELOClient() {
        super(logger);

        comment = java.util.ResourceBundle.getBundle(getClass().getName()).getString("Comment");
    }

    public java.util.List<WFCollectNode> getWorkflows(int max, String user) throws RemoteException {        
        int userId                                      = getUserId(user);
        String[] userIds                                = {String.valueOf(userId)};
        java.util.ArrayList<WFCollectNode> workflows    = new java.util.ArrayList<WFCollectNode>(max);        
        FindTasksInfo fi                                = new FindTasksInfo();
        
        fi.setInclWorkflows(true);        
        fi.setUserIds(userIds);
        
        FindResult result   = _connection.ix().findFirstTasks(fi, max);         
        UserTask[] tasks    = result.getTasks();

        for (UserTask task : tasks) {
            workflows.add(task.getWfNode());
        }
        
        _connection.ix().findClose(result.getSearchId());
        
        return workflows;
    }

    public void setMask(int objId, String mask) throws RemoteException {
        EditInfo info = _connection.ix().checkoutSord(Integer.toString(objId),
                EditInfoC.mbSord, LockC.NO);

        DocMask docMask = _connection.ix().checkoutDocMask(mask, DocMaskC.mbAll, LockC.NO);

        Sord sord = info.getSord();
        sord.setMask(docMask.getId());

        _connection.ix().checkinSord(sord, SordC.mbLean, LockC.NO);
    }

    public void initIXNegotiator() {
        de.di.barcodeserver.profiles.IXNegotiator.conn = _connection;
        de.di.barcodeserver.profiles.IXNegotiator.clientInfo = clientInfo;
    }

    public String getDocumentExt(int objId) throws RemoteException {
        String docExt = "";
        EditInfo info = _connection.ix().checkoutSord(Integer.toString(objId), EditInfoC.mbSordDoc, LockC.NO);
        Sord sord = info.getSord();
        docExt = sord.getDocVersion().getExt();

        return docExt;
    }

    public File getDocument(int objId, File tempDirectory) throws RemoteException {
        EditInfo info = _connection.ix().checkoutSord(Integer.toString(objId), EditInfoC.mbSordDoc, LockC.NO);
        Sord sord = info.getSord();
        if (sord.getType() < SordC.LBT_DOCUMENT || sord.getType() > SordC.LBT_DOCUMENT_MAX) {
            logger.info("The provided objid [ " + objId + " ] does not represent a document. Type is : " + sord.getType() + ", mask : " + sord.getMaskName());
            return null;
        }
        File f = null;
        try {            
            // f = File.createTempFile("WfAgent", "." + sord.getDocVersion().getExt(), tempDirectory); //ERSETZT MIT FOLGENDEM FRAGMENT (SL - 21.01.2020)
            
            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            /* SL - 21.01.2020
            generierte Nummer im Dateinamen ändern - Mit GUID ersetzen. WFAgent ist 7 Charakter lang
            */
            f = new File(tempDirectory.getAbsoluteFile()+"\\WFAgent"+sord.getGuid()+"."+sord.getDocVersion().getExt());
            f.createNewFile();             
            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++SL
            
            _connection.download(sord.getDocVersion().getUrl(), f);            
        } catch (IOException ex) {
            logger.error("Can not create Temp-File: " + f.getAbsolutePath() + " because " + ex.getMessage());
        }
        
        return f;
    }

    public String getShortDesc(String objId) throws RemoteException {
        EditInfo info = _connection.ix().checkoutSord(objId, EditInfoC.mbSord, LockC.NO);

        return info.getSord().getName();
    }

    public String getIndexValue(String objId, String index) throws RemoteException {
        EditInfo info = _connection.ix().checkoutSord(objId, EditInfoC.mbSord, LockC.NO);

        for (ObjKey key : info.getSord().getObjKeys()) {
            if (key.getName().equals(index)) {
                String[] data = key.getData();

                if (data != null && data.length > 0) {
                    return data[0];
                }

                return null;
            }
        }

        return null;
    }

    public void setIndexValue(String objId, String index, String value) throws RemoteException {
        logger.info("String objId: " + objId);
        EditInfo info = _connection.ix().checkoutSord(objId, EditInfoC.mbSord, LockC.NO);        
        for (ObjKey key : info.getSord().getObjKeys()) {
            if (key.getName().equals(index)) {
                String[] data = new String[1];
                data[0] = value;
                key.setData(data);
                break;
            }
        }

        _connection.ix().checkinSord(info.getSord(), SordC.mbLean, LockC.NO);
    }

    // Workflow forwarding 11.06.2013
    public void forwardWorkflow(int wfId, int nodeId, WFCollectNode node, ELOClient client) {
        try {
            // Begin edit workflow node
            WFEditNode editNode = _connection.ix().beginEditWorkFlowNode(wfId, nodeId, LockC.NO);
            int[] enterNodes;

            WFNode[] successorNodes = editNode.getSuccNodes();

            enterNodes = new int[successorNodes.length];
            int i = 0;
            for (WFNode succ : successorNodes) {
                enterNodes[i] = succ.getId();
                ++i;
            }

            // End edit workflow node
            client.endEditWorkflowNode(node.getFlowId(), node.getNodeId(), false, false,
                    node.getNodeName(), comment, enterNodes);
            logger.info("Forwarded workflow to next node");
        } catch (RemoteException rex) {
            logger.error("Error forwarding workflow " + rex.getMessage());
        }
    }

    public WFEditNode beginEditWorkflowNode(int wfId, int nodeId) throws RemoteException {
        return _connection.ix().beginEditWorkFlowNode(wfId, nodeId, LockC.NO);
    }

    public void endEditWorkflowNode(int wfId, int nodeId, boolean terminate,
            boolean cancel, String sName, String sComment, int[] enterNodes) throws RemoteException {
        _connection.ix().endEditWorkFlowNode(wfId, nodeId, terminate, cancel, sName, sComment,
                enterNodes);
    }

    public void takeWorkflowNode(int wfId, int nodeId, String userId) throws RemoteException {
        _connection.ix().takeWorkFlowNode(wfId, nodeId, userId, WFTakeNodeC.DEFAULT, LockC.NO);
    }

    public void startWorkFlow(String template, String workflow, String objId) throws RemoteException {
        _connection.ix().startWorkFlow(template, workflow, objId);
    }

    public boolean isActiveWorkflow(String template, String objId) throws RemoteException {
        FindWorkflowInfo info = new FindWorkflowInfo();
        info.setObjId(objId);
        info.setTemplateId(template);
        info.setType(WFTypeC.ACTIVE);

        FindResult result = _connection.ix().findFirstWorkflows(info, 1, WFDiagramC.mbLean);

        boolean isActive = false;

        if (result.getWorkflows().length > 0) {
            isActive = true;
        }

        _connection.ix().findClose(result.getSearchId());

        return isActive;
    }

    public WFDiagram checkoutWorkflow(int wfId) throws RemoteException {
        return _connection.ix().checkoutWorkFlow(Integer.toString(wfId),
                WFTypeC.ACTIVE,
                WFDiagramC.mbAll,
                LockC.YES);
    }

    public void checkinWorkflow(WFDiagram diagram) throws RemoteException {
        _connection.ix().checkinWorkFlow(diagram, WFDiagramC.mbAll, LockC.NO);
    }

    public int getUserId(String user) throws RemoteException {
        String[] users = {user};

        UserInfo[] info = _connection.ix().checkoutUsers(users,
                CheckoutUsersC.BY_IDS, LockC.NO);

        if (info != null && info.length > 0) {
            return info[0].getId();
        }

        throw new RemoteException("User '" + user + "' not found");
    }

    public EditInfo getAcl(int objId) throws RemoteException {
        return _connection.ix().checkoutSord(Integer.toString(objId), EditInfoC.mbSord, LockC.NO);
    }

    public void setAcl(EditInfo info) throws RemoteException {
        _connection.ix().checkinSord(info.getSord(), SordC.mbAllIndex, LockC.NO);
    }

    public void setIndexValueSpalte(String objId, String index, String values) throws RemoteException {
        EditInfo info = _connection.ix().checkoutSord(objId, EditInfoC.mbSord, LockC.NO);

        //Die Pilcrow am Anfang und Ende beseitigen
        String value = values;
        if ((value != null) && (value.length() > 0)) {
            if (value.startsWith("¶")) {
                value = value.substring(1);
            }
            if (value.substring(value.length() - 1, value.length()).equals("¶")) {
                value = value.substring(0, value.length() - 1);
            }
        }

        //werte speichern
        for (ObjKey key : info.getSord().getObjKeys()) {
            if (key.getName().equals(index)) {
                //
                String[] data = value.split("¶");
                key.setData(data);
                break;
            }
        }

        _connection.ix().checkinSord(info.getSord(), SordC.mbLean, LockC.NO);
    }

    public static void setKey(Sord sord, String key, String value) {
        if (sord == null) {
            logger.warn("Sord must not be null");
            return;
        }
        if (key == null || key.isEmpty()) {
            logger.warn("Key must not be null or empty");
            return;
        }
        if (value == null) {
            logger.warn("Value must not be null ");
            return;
        }
        for (ObjKey objKey : sord.getObjKeys()) {
            if (objKey.getName().equals(key)) {
                objKey.setData(value.split(PILLCROW));
                break;
            }
        }

    }
    
    // +++
    public String getIndexServerVersion() 
    {
        return this._connection.getImplVersion();                  
    }
    // +++ END MOD.
}
