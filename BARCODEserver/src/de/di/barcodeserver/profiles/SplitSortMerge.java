package de.di.barcodeserver.profiles;

import de.di.barcodeserver.elo.ELOClient;
import de.elo.ix.client.WFCollectNode;
import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Rahman
 */
public class SplitSortMerge extends BaseProfile {

    private File tempDirectory;
    private Logger logger;
    private Properties properties;

    public SplitSortMerge() {
        logger = Logger.getLogger(this.getClass().getPackage().getName());
    }

    public void init(Properties props) throws ProfileException {
        tempDirectory = new File(props.getProperty("TempDirectory"));
        try {
            tempDirectory.mkdir();
        } catch (SecurityException sec) {
            logger.error("Cannot create tempdirectory: " + tempDirectory.getAbsoluteFile() + " " + sec.getMessage());
        }
        properties = props;
    }

    public void handleWorkflow(WFCollectNode node) throws WorkflowHandlingException {

        ELOClient client = getClient();
        File file = null;
        try {
            long startTime = System.currentTimeMillis();
//            logger.info("old variant");
            client.initIXNegotiator();
            IXNegotiator.properties = properties;
//            HashMap<String, String> fieldsForMetaFile = IXNegotiator.getFieldsForMetaFile(node.getObjId(), properties);
//            HashMap<String, String> sordInformation = IXNegotiator.getSordInformation(node.getObjId(), properties);
//            deleteFolder(tempDirectory);
//            file = client.getDocument(node.getObjId(), tempDirectory);
            logger.info("Using profile name " + properties.getProperty("ProfileName"));
//            new SplitSortMergeSupporter().run(fieldsForMetaFile, sordInformation, file.getAbsolutePath(), objId, properties, logger);
//            logger.info("Document stack has been successfully splitted, sorted and merged");
//            client.setIndexValue(node.getObjGuid(), properties.getProperty("ResultFieldStatus", ""), properties.getProperty("ResultStatusOk", "OK"));
//            logger.debug("Set result status: " + properties.getProperty("ResultStatusOk", "OK"));
            logger.info("Node-Objekt: START" + node);
            logger.info("Node-Objekt-OBJID: START" + node.getObjId());
            logger.info("Node-Objekt-OBJID: START" + node.getObjGuid());
            file = client.getDocument(node.getObjId(), tempDirectory);   
            logger.info("Node-Objekt: ENDE" + node);
            //String standort = client.getIndexValue(node.getObjId()+"", "STANDORT");
            
            new SSMDocWorker(node.getObjId(), file.getAbsolutePath(), properties).work();            
            
            file.delete();
            file = null;
            client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);
            logger.info("Time: " + (System.currentTimeMillis() - startTime));
        } catch (Exception ex) {
            try {
                if (properties.containsKey("ResultFieldStatus") && properties.getProperty("ResultStatusError").length() != 0) {
                    logger.debug("Set result status: " + properties.getProperty("ResultStatusError", "ERROR"));
                    client.setIndexValue(String.valueOf(node.getObjId()), properties.getProperty("ResultFieldStatus"), properties.getProperty("ResultStatusError", "ERROR"));
                    // +++
                    if(file != null) {
                        file.delete();
                        file = null;
                        client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);                    
                    }
                    // +++
                } else {
                    logger.error("Profile " + properties.getProperty("ProfileName") + " either doesn't contain key 'ResultFieldStatus' or key hasn't value");
                    logger.error("SplitSortMergeSupporter.run throws exception: " + ex.getMessage());
                    // +++
                    if(file != null) {
                        file.delete();
                        file = null;
                        client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);                    
                    }                   
                    // +++
                }
            } catch (Exception x) {
                logger.error("SplitSortMergeSupporter.run throws exception: " + ex.getMessage());
                throw new WorkflowHandlingException("Unable to handle workflow " + x.getMessage());                
            }
//            client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);
            if (file.delete()) {
                logger.info("File " + file.getName() + " is now deleted from " + file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - file.getName().length()));
            } else {
                logger.info("Cannot delete file : " + file.getName() + "  from : " + file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - file.getName().length()));
            }
            throw new WorkflowHandlingException("Unable to handle workflow " + ex.getMessage());
        }
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
    }
}
