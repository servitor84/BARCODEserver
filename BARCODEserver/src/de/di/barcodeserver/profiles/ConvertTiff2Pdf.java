package de.di.barcodeserver.profiles;

import com.lowagie.text.pdf.PdfReader;
import java.io.File;
import java.util.Properties;
import org.apache.log4j.Logger;
import de.elo.utils.net.RemoteException;
//import com.lowagie.text.pdf.PdfReader;
import de.elo.ix.client.WFCollectNode;
import de.di.barcodeserver.elo.ELOClient;

/**
 *
 * @author Rahman
 */
public class ConvertTiff2Pdf extends BaseProfile {

    private Logger logger;
    private File tempDirectory;
    private Properties properties;
    public static WFCollectNode node;

    public ConvertTiff2Pdf() {
        logger = Logger.getLogger(this.getClass().getPackage().getName());
    }

    public void init(Properties props) throws ProfileException {
        tempDirectory = new File(props.getProperty("TempDirectory"));
        try {
            tempDirectory.mkdir();
        } catch (SecurityException sec) {
            logger.error("Cannot create tempdirectory " + tempDirectory.getAbsoluteFile().toString() + " because " + sec);
        }
        properties = props;
    }

    public void handleWorkflow(WFCollectNode node) throws WorkflowHandlingException {        
        ELOClient client = getClient();
        File docFile = null;
        try {            
            docFile = client.getDocument(node.getObjId(), tempDirectory);            
            if (client.getDocumentExt(node.getObjId()).equalsIgnoreCase("tif")) {                
                client.initIXNegotiator();
                logger.info("Using profile name " + properties.getProperty("ProfileName"));
                int numberOfPages = IXNegotiator.convertTiff2Pdf(node.getObjId(), logger, properties, docFile);
                logger.info("Document successfully converted to PDF");
                logger.debug("Set result status: " + properties.getProperty("ResultStatusOk", "OK"));
                client.setIndexValue(Integer.toString(node.getObjId()), properties.getProperty("ResultFieldStatus"), properties.getProperty("ResultStatusOk", "OK"));
                client.setIndexValue(Integer.toString(node.getObjId()), properties.getProperty("ResultFieldPagecount"), String.valueOf(numberOfPages));
                client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);
            } else if (client.getDocumentExt(node.getObjId()).equalsIgnoreCase("pdf")) {
                logger.info("Document is " + client.getDocumentExt(node.getObjId()) + ", skip workflow task.");
                try {
                    logger.debug("Set result status: " + properties.getProperty("ResultStatusOk", "OK"));
                    PdfReader reader = new PdfReader(docFile.getAbsolutePath().toString());
                    client.setIndexValue(Integer.toString(node.getObjId()), properties.getProperty("ResultFieldStatus"), properties.getProperty("ResultStatusOk", "OK"));
                    client.setIndexValue(Integer.toString(node.getObjId()), properties.getProperty("ResultFieldPagecount"), String.valueOf(reader.getNumberOfPages()));
                    client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);
                } catch (RemoteException ex) {
                    logger.error("Error setting status for document" + ex.getMessage());
                    throw new WorkflowHandlingException("Unable to handle workflow " + ex.getMessage());
                }
            } else {
                logger.warn("Document is " + client.getDocumentExt(node.getObjId()) + ", skip workflow task.");
                logger.debug("Set result status: " + properties.getProperty("ResultStatusError", "ERROR"));
                client.setIndexValue(Integer.toString(node.getObjId()), properties.getProperty("ResultFieldStatus"), properties.getProperty("ResultStatusError", "ERROR"));
                client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);
            }
        } catch (Exception ex) {
            if (docFile != null) {
                docFile.delete();
            }
            try {
                client.setIndexValue(Integer.toString(node.getObjId()), properties.getProperty("ResultFieldStatus"), properties.getProperty("ResultStatusError", "ERROR"));
            } catch (Exception xe) {
                throw new WorkflowHandlingException("Unable to handle workflow " + xe.getMessage());
            }
            logger.error("Working on document with ID " + node.getObjId() + ", ConvertTiff2Pdf has failed " + ex.getMessage());
            client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);
            throw new WorkflowHandlingException("Unable to handle workflow " + ex.getMessage());
        }
        if (docFile != null) {
            docFile.delete();
        }
    }
}