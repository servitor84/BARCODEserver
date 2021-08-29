package de.di.barcodeserver.profiles;

//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Rectangle;
//import com.lowagie.text.pdf.PdfContentByte;
//import com.lowagie.text.pdf.PdfImportedPage;
//import com.lowagie.text.pdf.PdfReader;
//import com.lowagie.text.pdf.PdfWriter;
import de.di.barcodeserver.elo.ELOClient;
import de.elo.ix.client.LockC;
import de.elo.ix.client.ObjKey;
import de.elo.ix.client.Sord;
import de.elo.ix.client.SordC;
import de.elo.utils.net.RemoteException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;
//import org.apache.pdfbox.multipdf.PDFMergerUtility;

//import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.pdfbox.util.Splitter;

public class SSMDocWorker {

    private static final Logger logger = Logger.getLogger(SSMDocWorker.class);
    private int objId;
    private String filePath;
    private Properties properties;
    private Sord sord;
    private final LinkedHashMap<String, ArrayList<Integer>> barcodeToPages;
    private final HashMap<Integer, String> pageToBarcode;
    private final HashMap<String, String> sordMetadata;
    private static final String PILLCROW = "¶";
    private static SimpleDateFormat eloFormat = new SimpleDateFormat("yyyyMMdd");
    
    // Für Kunde CEVA
    //private String standort;
    //        

    public SSMDocWorker(int objId, String filePath, Properties properties) {
        this.sordMetadata = new HashMap();
        this.pageToBarcode = new HashMap();
        this.barcodeToPages = new LinkedHashMap<String, ArrayList<Integer>>();
        setObjId(objId);
        setFilePath(filePath);
        setProperties(properties);
        
        //setStandort(standort);
    }

    private void initiateMetadata() {
        logger.trace("Initiate metadataStart");
        for (ObjKey objKey : this.sord.getObjKeys()) {
            if (objKey.getName().equalsIgnoreCase(this.properties.getProperty("BarcodeContent"))) {
                this.sordMetadata.put(objKey.getName(), StringUtils.join(objKey.getData(), PILLCROW));
            } else {
                this.sordMetadata.put(objKey.getName(), StringUtils.join(objKey.getData(), ""));
            }
        }
    }

    private void initBarcodeAssociation() {
        String barcodes = (String) this.sordMetadata.get(this.properties.getProperty("BarcodeContent"));
        logger.trace("Barcodes read: " + barcodes);
        logger.trace("Creating page --> barcode map");
        for (String barcodeData : barcodes.split("¶")) {
            if (barcodeData.contains(";")) {
                String barcode = barcodeData.split(";")[0];
                if (barcode == null || barcode.isEmpty()) {
                    continue;
                }
                Integer pageNumber = Integer.valueOf(Integer.parseInt(barcodeData.split(";")[1]));
                logger.trace("Page " + pageNumber + " to barcode " + barcode);
                this.pageToBarcode.put(pageNumber, barcode);
            }
        }
        String nbPages = (String) this.sordMetadata.get(this.properties.getProperty("ResultFieldPagecount"));
        logger.trace("Number of pages from sord metadata: " + nbPages);
        if (nbPages.isEmpty()) {
            logger.warn("No value found for numberOfPages");
            nbPages = "0";
        }
        logger.trace("Dealing with pages without barcodes");
        int numberOfPages = Integer.parseInt(nbPages);
        for (int i = 1; i <= numberOfPages; i++) {
            if (this.pageToBarcode.get(Integer.valueOf(i)) == null) {
                logger.trace("Adding page " + i + " to barcode " + this.pageToBarcode.get(Integer.valueOf(i - 1)));
                this.pageToBarcode.put(Integer.valueOf(i), this.pageToBarcode.get(Integer.valueOf(i - 1)));
            }
        }
        logger.trace("Creating barcode --> list of pages map");
        for (Integer pageId : this.pageToBarcode.keySet()) {
            String barcode = (String) this.pageToBarcode.get(pageId);
            logger.trace("Initializing page list for barcode " + barcode);
            if (this.barcodeToPages.get(barcode) == null) {
                this.barcodeToPages.put(barcode, new ArrayList());
            }
            logger.trace("Adding page " + pageId + " to page list of barcode " + barcode);
            ((ArrayList) this.barcodeToPages.get(barcode)).add(pageId);
        }

        for (String barcode : this.barcodeToPages.keySet()) {
            ArrayList<Integer> pages = this.barcodeToPages.get(barcode);
            if (pages.size() > 1) {
                System.out.println(barcode + ": " + StringUtils.join(pages, ","));
            }
            boolean outOfOrder = false;
            for (int i = 1; i < pages.size(); i++) {
                int page = pages.get(i);
                int previous = pages.get(i - 1);
                if (page < previous) {
                    System.out.println("Pages in barcode " + barcode + " are not in order: " + page + " after " + previous);
                    logger.trace("Pages in barcode " + barcode + " are not in order: " + page + " after " + previous);
                    outOfOrder = true;
                    break;
                }
            }
            if (outOfOrder) {
                Collections.sort(pages);
                logger.trace("Pages in barcode " + barcode + " ordered:" + StringUtils.join(pages, ","));
            }
        }
    }

    private void initDirectory() {
        String outputDirectory = this.properties.getProperty("OutputDirectory");
        if (outputDirectory == null) {
            throw new UnsupportedOperationException("Output directory not set in config");
        }
        File file = new File(outputDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void initBarcodeToPages() {
        initiateMetadata();
        initBarcodeAssociation();
        initDirectory();
    }

    private void splitPdf() throws IOException {
        try {
            File file = new File(this.filePath);
            PDDocument document;
            document = PDDocument.load(file);            
            Splitter splitter = new Splitter();
            List<PDDocument> splits = splitter.split(document);
            int count = 0;            
            List<PDDocument> Pages = splitter.split(document);
            for (String barcode : this.barcodeToPages.keySet()) {
                count++;
                List<Integer> pages = this.barcodeToPages.get(barcode);
                PDDocument doc = new PDDocument();
                PDFMergerUtility u = new PDFMergerUtility();
                for (int i = 0; i < pages.size(); i++) {
                    u.appendDocument(doc, splits.get(pages.get(i) - 1));
                }
                String fileName = this.properties.getProperty("OutputDirectory") + File.separator + this.properties.getProperty("SplitFilePrefix", "") + this.objId + "__" + barcode.replace("/", "") + "__" + count + ".pdf";
                File outputFile = new File(fileName);
                try {
                    doc.save(fileName);
                } catch (COSVisitorException ex) {}
                logger.info("Succesfully generated pdf");
                generateTxtFile(barcode, count);
                generateSigFile(barcode, count);
            }
            document.close();
            logger.info("Succesfully added necesary files");
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(SSMDocWorker.class.getName()).log(Level.SEVERE, null, ex);  
            throw new IOException();
        }
    }

//    private void generatePdfs(PdfReader reader)
//            throws IOException, DocumentException {
//        int count = 0;
//        for (String barcode : this.barcodeToPages.keySet()) {
//            count++;
//            try {
//                Document document = new Document();//reader.getPageSizeWithRotation(1));
//                String fileName = this.properties.getProperty("OutputDirectory") + File.separator + this.properties.getProperty("SplitFilePrefix", "") + this.objId + "-" + barcode + "-" + count + ".pdf";
//                logger.info("\tTrying to generate: " + fileName + " ,pages: " + StringUtils.join((Iterable) this.barcodeToPages.get(barcode), ","));
//
//                OutputStream outputStream = new FileOutputStream(fileName);
//                PdfWriter writer = PdfWriter.getInstance(document, outputStream);
//
//                PdfContentByte cb = null;
//                for (Integer pageId : this.barcodeToPages.get(barcode)) {
//                    if (!document.isOpen()) {
//                        document.open();
//                        cb = writer.getDirectContentUnder();
//                    } else {
//                        document.newPage();
//                    }
//
//                    document.setPageSize(reader.getPageSizeWithRotation(pageId));
//
//                    PdfImportedPage page = writer.getImportedPage(reader, pageId);
//
//                    logger.trace("Page " + pageId + ": [" + reader.getPageSizeWithRotation(pageId).getWidth() + " , " + reader.getPageSizeWithRotation(pageId).getHeight() + "] * " + reader.getPageSizeWithRotation(pageId).getRotation());
//
//                    int rotation = reader.getPageRotation(pageId);
//                    if (rotation == 90 || rotation == 270) {
//                        cb.addTemplate(page, 0f, -1f, 1f, 0, 0, reader.getPageSizeWithRotation(pageId).getHeight());
//                    } else {
//                        cb.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
//                    }
//                    
//                }
//                outputStream.flush();
//                document.close();
//                outputStream.close();
//                logger.info("Succesfully generated pdf");
//                generateTxtFile(barcode, count);
//                generateSigFile(barcode, count);
//                logger.info("Succesfully added necesary files");
//            } catch (IOException ex) {
//                logger.error(ex);
//                throw ex;
//            } catch (DocumentException ex) {
//                logger.error(ex);
//                throw ex;
//            }
//        }
//    }
    private void generateTxtFile(String barcode, int count)
            throws IOException {
        if (this.properties.getProperty("CreateTXT", "").equalsIgnoreCase("true")) {
            String textFileName;
            if (this.properties.getProperty("BarcodeMode", "").equalsIgnoreCase("complex")) {
                textFileName = this.properties.getProperty("OutputDirectory") + File.separator + this.properties.getProperty("SplitFilePrefix", "") + this.objId + "__" + barcode.replace("/", "") + ".txt";
            } else {
                textFileName = this.properties.getProperty("OutputDirectory") + File.separator + this.properties.getProperty("SplitFilePrefix", "") + this.objId + "__" + barcode.replace("/", "") + "__" + count + ".txt";
            }
            logger.info("\tTextFileName: " + textFileName);
            try {
                PrintWriter textFile = new PrintWriter(textFileName, "UTF-8");
                logger.trace("\t\tAfter opening file for write. Fields: " + this.properties.getProperty("CreateTXT.FieldsForMetaFile", ""));
                StringTokenizer st = new StringTokenizer(this.properties.getProperty("CreateTXT.FieldsForMetaFile", ""), ",");
                while (st.hasMoreTokens()) {
                    String keyName = st.nextToken();
                    logger.trace("\t\t\tWriting metadata key: " + keyName + " ,value: " + (String) this.sordMetadata.get(keyName));
                    textFile.println("##" + keyName + "=" + (String) this.sordMetadata.get(keyName) + "##");
                }
                logger.trace("\t\tAfter writing metadata ");
                textFile.println("##PDFPAGECOUNT=" + ((ArrayList) this.barcodeToPages.get(barcode)).size() + "##");
                textFile.println("##TEMPLATE=" + this.properties.getProperty("CreateTXT.XMLtemplate") + "##");
                textFile.println("##BC=" + barcode + "##");

                StringBuilder sb = new StringBuilder("¶");
                for (Integer pageId : this.barcodeToPages.get(barcode)) {
                    sb.append(barcode).append(";").append(pageId).append("¶");
                }
                sb.replace(sb.length() - 1, sb.length(), "");
                textFile.println("##BCCONTENTSSM=" + sb.toString() + "##");
                textFile.println("##PAGES=" + StringUtils.join((Iterable) this.barcodeToPages.get(barcode), ",") + "##");
                textFile.write("##DATE=" + eloFormat.format(new Date()) + "##");

                textFile.flush();
                textFile.close();
            } catch (IOException ex) {
                logger.error(ex);
                throw ex;
            }
        }
    }

    private void generateSigFile(String barcode, int count)
            throws IOException {
        if (this.properties.getProperty("CreateSIG", "").equalsIgnoreCase("true")) {
            String sigFileName = "";
            if ((this.properties.containsKey("BarcodeMode")) && (this.properties.getProperty("BarcodeMode").equalsIgnoreCase("complex"))) {
                sigFileName = this.properties.getProperty("OutputDirectory") + File.separator + this.properties.getProperty("SplitFilePrefix", "") + this.objId + "__" + barcode.replace("/", "") + ".sig";
            } else {
                sigFileName = this.properties.getProperty("OutputDirectory") + File.separator + this.properties.getProperty("SplitFilePrefix", "") + this.objId + "__" + barcode.replace("/", "") + "__" + count + ".sig";
            }
            try {
                PrintWriter sigFile = new PrintWriter(sigFileName, "UTF-8");
                sigFile.println("");
                sigFile.close();
            } catch (IOException ex) {
                logger.error("Cannot create SIG file for splitted PDF file " + ex.getMessage());
                throw ex;
            }
        }
    }

    public void work()
            throws Exception {
        this.sord = IXNegotiator.checkoutSord(this.objId);
        if (this.sord != null) {
            try {
                logger.trace("Trying to open pdf :" + this.filePath);
//                PdfReader reader = new PdfReader(this.filePath);
                initBarcodeToPages();
                logger.info("Succesfully initiated barcodetopages");
//                generatePdfs(reader);
                splitPdf();
                logger.trace("After generating pdfs");
//                reader.close();
                ELOClient.setKey(this.sord, this.properties.getProperty("ResultFieldStatus", ""), this.properties.getProperty("ResultStatusOk", "OK"));
                IXNegotiator.conn.ix().checkinSord(this.sord, SordC.mbAll, LockC.NO);
                logger.trace("After checkin sord");
            } catch (IOException ex) {
                try {
                    logger.error("Error reading the pdfFile");

                    ELOClient.setKey(this.sord, this.properties.getProperty("ResultFieldStatus"), this.properties.getProperty("ResultStatusError", "ERROR"));
                    IXNegotiator.conn.ix().checkinSord(this.sord, SordC.mbAll, LockC.NO);
                    throw ex;
                } catch (RemoteException ex1) {
                    logger.error("Error saving sord metadata");
                    throw ex1;
                }
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            logger.warn("No work done due to null sord");
        }
    }

    public int getObjId() {
        return this.objId;
    }

    public final void setObjId(int objId) {
        logger.trace("\tSetting objId: " + objId);
        this.objId = objId;
    }
    
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    public String getStandort() {
//        return this.standort;
//    }
//
//    public final void setStandort(String standort) {
//        logger.trace("\tSetting standort: " + standort);
//        this.standort = standort;
//    }
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public String getFilePath() {
        return this.filePath;
    }

    public final void setFilePath(String filePath) {
        logger.trace("\tSetting filePath: " + filePath);
        this.filePath = filePath;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public final void setProperties(Properties properties) {
        if (logger.isDebugEnabled()) {
            logger.trace("\tSetting properties to: ");
            for (String key : properties.stringPropertyNames()) {
                logger.trace("\t\t" + key + "=" + properties.getProperty(key));
            }
        }
        this.properties = properties;
    }

//    public static void main(String[] args)
//            throws IOException, DocumentException {
//
//        long startTime = System.currentTimeMillis();
//        String bcsString = "4711;1¶;2¶;3¶4712";
//        Properties properties = new Properties();
//        properties.setProperty("OutputDirectory", "C:/DOKinform/BARCODEserver/SSM/output");
//        properties.setProperty("BarcodeContent", "BARCODE");
//        properties.setProperty("ResultFieldPagecount", "PAGECOUNT");
//            SSMDocWorker sSMDocWorker = new SSMDocWorker(0, "C:\\Users\\ELO1\\Documents\\Projects related\\TIF2PDF-3pages.pdf", properties);
//            sSMDocWorker.sordMetadata.put("BARCODE", bcsString);
//            sSMDocWorker.sordMetadata.put("PAGECOUNT", "3");
//            sSMDocWorker.initDirectory();
//            sSMDocWorker.initBarcodeAssociation();
//            File file = new File("C:/Users/ELO1/Documents/Projects related/TIF2PDF-3pages.pdf");
//            InputStream is = new FileInputStream(file);
//            try{PdfReader reader = new PdfReader(is);
//            sSMDocWorker.generatePdfs(reader);}
//            catch(Exception ex){
//                int x = 0; x++;
//            }
//        
//    }
}
