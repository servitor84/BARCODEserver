package de.di.barcodeserver.profiles;

import com.lowagie.text.pdf.PdfReader;
import de.di.barcodeserver.barcode.BarcodeProcessor;
import de.di.barcodeserver.barcode.Info;
import de.di.barcodeserver.elo.ELOClient;
import static de.di.barcodeserver.profiles.ReadBarcode.firstTimeRuning;
import de.elo.ix.client.WFCollectNode;
import de.elo.utils.net.RemoteException;
import java.awt.Point;
import java.io.File;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.log4j.Logger;
import qualitysoft.qsBarcode.Defines;

/**
 *
 * @author samir.lebaal
 */
public class ReadQRCode extends BaseProfile {
    private Logger logger;
    private static String comment = null;      
    private Point upperLeft;
    private Point lowerRight;    
    private SearchArea area = SearchArea.Page;
    private SearchMode searchMode = SearchMode.All;
    private int[] pageList;
    private Integer actuelResultFieldPageCount;
    private String mask;
    private String resultFieldBarcodeCount;
    private String ssmInfoField = "";
    private Properties properties;
    private String resultFieldData;
    private String resultFieldStatus;
    private String resultFieldPagecount;
    private String resultFieldPDFPagecount;
    private File tempDirectory;
    
    public ReadQRCode() {
        logger = Logger.getLogger(getClass());
        synchronized (this) {
            if (comment == null) {
                comment = java.util.ResourceBundle.getBundle(getClass().getName()).getString("Comment");
            }
        }
    }
    
    private enum SearchMode {

        First, All, List
    };

    private enum SearchArea {

        Page, Area
    };

    private enum BarcodeMode {

        All, Group, List
    };       
    
    private void run(ELOClient client, File docFile, WFCollectNode node) throws WorkflowHandlingException, java.io.IOException {
       java.util.List<String> result = null;
       BarcodeProcessor reader = null;
       try {
           reader = BarcodeProcessor.getInstance();
       } catch (Exception readerException) {
           logger.debug("Error initialize Barcode reader: " + readerException.getMessage());
       }
       reader.initBarcode2();
       firstTimeRuning = false;
       if (area == SearchArea.Area) {
           java.awt.Rectangle rec = new java.awt.Rectangle(upperLeft);
           rec.add(lowerRight);
           reader.setScanArea(rec);
       }      
       logger.info("ReadBarcode run : " + docFile.getName() + ", length : " + docFile.length() + ", canRead : " + docFile.canRead());
       if (docFile.length() > 0 && docFile.canRead()) {
           if (searchMode == SearchMode.First) {
               result = reader.readBarcodes(docFile, 1);
               URL url = new File(docFile.getAbsolutePath()).toURI().toURL();
               PdfReader pdfReader = new PdfReader(url);
               int page = pdfReader.getNumberOfPages();
               setMaskfieldsAndForwardWF(client, node, docFile, result, page);
           } else {
               result = new java.util.LinkedList<String>();
               int page = 1;                
               java.util.List<String> pageResult = reader.readBarcodes(docFile, page);                
               while (pageResult != null) {
                   result.addAll(pageResult);
                   ++page;
                   pageResult = reader.readBarcodes(docFile, page);
               }                   
               if (pageResult != null || page > 1) {
                   setMaskfieldsAndForwardWF(client, node, docFile, result, page);
               }
           }
       } else {
           logger.error("Tempfile not found skip workflow task.");
           throw new WorkflowHandlingException("Error while workflow document is empty");
       }
    }
    
    private void setMaskfieldsAndForwardWF(ELOClient client, WFCollectNode node, File docFile, java.util.List<String> result, int page) throws WorkflowHandlingException{                                              
        firstTimeRuning = true;
        logger.info("Write data to ELO");
        actuelResultFieldPageCount = page; 
        docFile.setWritable(true);
        if (!docFile.delete()) {
            logger.info("Temporary file cannot be deleted: " + docFile.getAbsolutePath());
        }
        int resultSize = result.size();
        try {
            client.setMask(node.getObjId(), mask);
            logger.info("Set mask");
        } catch (RemoteException ex) {
            logger.error("Error setting mask " + mask + " for document", ex);
            throw new WorkflowHandlingException("Error while setting the document mask");
        }
        try {
            logger.info("Result count: " + resultSize);
            client.setIndexValue(node.getObjGuid(), resultFieldBarcodeCount,
                    Integer.toString(resultSize));
            if (!ssmInfoField.isEmpty() && resultSize == page - 1) {
                client.setIndexValue(node.getObjGuid(), ssmInfoField,
                        "YES");
            } else if (!ssmInfoField.isEmpty()) {
                client.setIndexValue(node.getObjGuid(), ssmInfoField,
                        "NO");
            }
        } catch (RemoteException ex) {
            logger.error("Error setting barcode count for document", ex);
            throw new WorkflowHandlingException("Error while setting barcode count");
        }
        try {
            StringBuffer buffer = new StringBuffer();
            boolean first = true;

            for (String code : result) {
                if (first) {
                    buffer.append(code);
                    first = false;
                } else {
                    buffer.append(',');
                    buffer.append(code);
                }
            }
            logger.info("Result: " + buffer.toString());                       
            String ResultString = new String();
            ResultString = buffer.toString();                                              
            if(properties.getProperty("HidePagesNrByKeywording").equalsIgnoreCase("TRUE")) {
                int pageNr = ResultString.indexOf("¶")+1;
                String[] SplittedResultString = ResultString.split(",");
                String temp = "";
                for(int i = 0; i < SplittedResultString.length; i++) {
                    char cPageNr = SplittedResultString[i].charAt(pageNr);
                    SplittedResultString[i] = SplittedResultString[i].replace("¶"+cPageNr, "");
                    if(i != SplittedResultString.length-1) {
                        temp += SplittedResultString[i]+",";
                    } else {
                        temp += SplittedResultString[i];
                    }
                }
                ResultString = temp;
                ResultString = ResultString.replace("¶"+Integer.toString(pageNr), "");   
                ResultString = ResultString.replace(',', '¶');
            } else {
                ResultString = ResultString.replace('¶', ';');                           
                ResultString = ResultString.replace(',', '¶');                           
            }
            logger.debug("ResultString: " + ResultString);
            client.setIndexValueSpalte(node.getObjGuid(), resultFieldData, ResultString);                       
            if(properties.getProperty("HidePagesNrByKeywording").equalsIgnoreCase("FALSE")) {
                try {
                    logger.debug("Set result status: " /*Status aus Profile lesen */);
                    if (pageOneHasNoBarcode(ResultString)) {
                        client.setIndexValue(node.getObjGuid(), resultFieldStatus, properties.getProperty("ResultStatusError", "BcvalidateError"));
                    } else {
                        client.setIndexValue(node.getObjGuid(), resultFieldStatus, properties.getProperty("ResultStatusOk", "BCFINISHED"));
                    }
                } catch (RemoteException ex) {
                    logger.error("Error setting status for document", ex);
                    throw new WorkflowHandlingException("Error while setting result status");
                }
            } else {
                try {
                    client.setIndexValue(node.getObjGuid(), resultFieldStatus, properties.getProperty("ResultStatusOk", "BCFINISHED"));
                } catch(RemoteException ex) {
                    logger.error("Error setting status for document", ex);
                    throw new WorkflowHandlingException("Error while setting result status");
                }
            }       
            // TIFF: Anzahl Seiten
            try {
                if(!properties.getProperty("SearchPages").equals("First")) {
                    actuelResultFieldPageCount--;
                }
                logger.info("Set page count: " + actuelResultFieldPageCount);
                client.setIndexValue(node.getObjGuid(), resultFieldPagecount, actuelResultFieldPageCount.toString());
            } catch (RemoteException ex) {
                logger.error("Error setting page count for document ", ex);
                throw new WorkflowHandlingException("Error while setting page count");
            }
            // PDF: ANzahl Seiten
            try {                           
                logger.info("Set page count: " + actuelResultFieldPageCount);
                client.setIndexValue(node.getObjGuid(), resultFieldPDFPagecount, actuelResultFieldPageCount.toString());
            } catch (RemoteException ex) {
                logger.error("Error setting page count for document ", ex);
                throw new WorkflowHandlingException("Error while setting page count");
            }
        } catch (RemoteException ex) {
            logger.error("Error setting barcode data for document ", ex);
            throw new WorkflowHandlingException("Error while setting barcode data");
        }
        //forward workflow
        client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);        
    }
    
    public void handleWorkflow(WFCollectNode node) throws WorkflowHandlingException {
        ELOClient client = getClient();
        File docFile = null;
        try {
            docFile = client.getDocument(node.getObjId(), tempDirectory);
            if (docFile != null) {
                run(client, docFile, node);
            }
        } catch (RemoteException ex) {
            logger.error("Unable to retrieve document from workflow " + node.getFlowName() + " because : " + ex.getMessage());
            logger.warn("Skip workflow : " + node.getFlowName() + " with ID : " + node.getFlowId());
            client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);
            throw new WorkflowHandlingException("Unable to retrieve workflow document");
        } catch (java.io.IOException ioex) {
            logger.error("Error while handling document file for workflow " + node.getFlowName() + " because : " + ioex.getMessage());
            logger.warn("Skip workflow : " + node.getFlowName() + " with ID : " + node.getFlowId());
            //client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);
            throw new WorkflowHandlingException("Error while handling document file");
        } catch (NullPointerException nullEx) {
            logger.error("The workflow with wfId: " + node.getFlowId() + " / " + node.getFlowName() + " / has no physical document " + nullEx.getMessage());
            logger.warn("Skip workflow task");
            throw new WorkflowHandlingException("Error while handling workflow");
        } catch (Exception exc) {
            logger.error("Unable to retrieve document from ELO " + node.getFlowName() + " because : " + exc.getMessage());
            logger.warn("Skip workflow : " + node.getFlowName() + " with ID : " + node.getFlowId());
            client.forwardWorkflow(node.getFlowId(), node.getNodeId(), node, client);
            throw new WorkflowHandlingException("Error while handling workflow");
        }
    }
    
    private boolean pageOneHasNoBarcode(String resultString) {        
        if (resultString.contains(";1")) {
            if (resultString.contains(";1¶")) {
                return false;
            } else {
                String tmp = resultString;
                String result = "";
                while (!tmp.substring(tmp.length() - 1).equals(";")) {
                    result = tmp.substring(tmp.length() - 1) + result;
                    tmp = tmp.substring(0, tmp.length() - 1);
                }
                if (Integer.valueOf(result) == 1) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }
    }
    
    private void parseSearchArea(String area) throws ProfileException {
        if (area.equalsIgnoreCase("page")) {
            this.area = SearchArea.Page;
        } else {
            this.area = SearchArea.Area;
            String[] coordinates = area.split(",");
            if (coordinates.length < 4) {
                throw new ProfileException("Illegal search area for profile");
            }
            try {
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);

                upperLeft = new Point(x, y);

                x = Integer.parseInt(coordinates[2]);
                y = Integer.parseInt(coordinates[3]);

                lowerRight = new Point(x, y);
            } catch (NumberFormatException nfe) {
                throw new ProfileException("Illegal coordinate for search area");
            }
        }
    }
    
     public void init(Properties props) throws ProfileException {
        properties = props;
        parseSearchArea(props.getProperty("SearchArea", "Page"));
        mask = props.getProperty("ChangeMask", "").trim();
        if (mask.length() == 0) {
            throw new ProfileException("Missing value for ChangeMask");
        }
        resultFieldData = props.getProperty("ResultFieldData", "").trim();
        if (resultFieldData.length() == 0) {
            throw new ProfileException("Missing value for ResultFieldData");
        }
        if (props.containsKey("SSMREADY")) {
            ssmInfoField = props.getProperty("SSMREADY");
        }
        resultFieldBarcodeCount = props.getProperty("ResultFieldBCcount", "").trim();
        if (resultFieldBarcodeCount.length() == 0) {
            throw new ProfileException("Missing value for ResultFieldBCcount");
        }
        resultFieldStatus = props.getProperty("ResultFieldStatus", "").trim();
        if (resultFieldStatus.length() == 0) {
            throw new ProfileException("Missing value for ResultFieldStatus");
        }
        resultFieldPagecount = props.getProperty("ResultFieldPagecount", "").trim();
        if (resultFieldPagecount.length() == 0) {
            throw new ProfileException("Missing value for ResultFieldPagecount");
        }        
        resultFieldPDFPagecount = props.getProperty("ResultFieldPDFPagecount", "").trim();
        if (resultFieldPDFPagecount.length() == 0) {
            throw new ProfileException("Missing value for ResultFieldPDFPagecount");
        }
        if (Info.getConfProperty("TemporaryDirectory") != null) {
            tempDirectory = new File(Info.getConfProperty("TemporaryDirectory"));
            logger.debug("Found in config TemporaryDirectory : " + Info.getConfProperty("TemporaryDirectory"));
            logger.debug("TemporaryDirectory exists : " + tempDirectory.exists());
        } else {
            tempDirectory = new File("\\..\\..\\temp");
        }
        try {
            if (tempDirectory.mkdir()) {
                logger.info("Temporary directory created");
            }
        } catch (SecurityException sec) {
            logger.error("Can not create Temp-Directory " + tempDirectory.getAbsoluteFile() + sec.getMessage());
        }
        // String searchPages = Info.getConfProperty("SearchPages");
        String searchPages = properties.getProperty("SearchPages");
        
        if (searchPages != null){
            if (searchPages.toLowerCase().equals("first")) { searchMode = SearchMode.First; }
            else if (searchPages.toLowerCase().equals("list")) { searchMode = SearchMode.List; }
            else { searchMode = SearchMode.All;}
        }
    }
    
}
