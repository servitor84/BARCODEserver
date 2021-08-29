package de.di.barcodeserver.profiles;

import com.lowagie.text.pdf.PdfReader;
import java.io.File;
import java.util.HashMap;
import de.elo.ix.client.*;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Rahman
 */
public class IXNegotiator {

    public static IXConnection conn;
    private static Logger logger = Logger.getLogger(IXNegotiator.class);
    private static File tiffFile;
    public static Properties properties;
    public static ClientInfo clientInfo;

    // Work out the sord from archive and return the file with sord from document 
    private static HashMap<String, Object> readDoc(int objId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            EditInfo sordInfo = conn.ix().checkoutSord( Integer.toString(objId), EditInfoC.mbAll, LockC.NO);
            Sord sord = sordInfo.getSord();
            map.put("sord", sord);
        } catch (Exception ex) {
            logger.error("Cannot CheckoutDoc : " + ex.getMessage());
            return map;
        }
        return map;
    }

    public static Sord checkoutSord(final int objId) {
        Sord ret = null;
        logger.debug("Requesting sord with id: " + objId);
        try {
            ret = conn.ix().checkoutSord( String.valueOf(objId), EditInfoC.mbAll, LockC.NO).getSord();
        } catch (Exception ex) {
            logger.warn("Error on checkoutSord with id: " + objId + ".Printing stacktrace");
            logger.warn(ex, ex);
        }
        return ret;
    }

    private static void insertDoc(File pdfFile, Sord tiffSord, int objId) {
        try {
            String parentId = String.valueOf(tiffSord.getParentId());
            EditInfo ed = conn.ix().createDoc( parentId, "", null, EditInfoC.mbSordDocAtt);
            Sord pdfSord = ed.getSord();
            pdfSord = tiffSord;
            Document doc = new Document();
            DocVersion dv = new DocVersion();
            dv.setPathId(pdfSord.getPath());
            dv.setEncryptionSet(pdfSord.getDetails().getEncryptionSet());
            dv.setExt(conn.getFileExt(pdfFile.toString()));
            doc.setDocs((new DocVersion[]{dv}));
            doc = conn.ix().checkinDocBegin( doc);
            dv = doc.getDocs()[0];
            String url = dv.getUrl();
            String uploadResult = conn.upload(url, pdfFile);
            dv.setUploadResult(uploadResult);
            doc = conn.ix().checkinDocEnd( pdfSord, SordC.mbAll, doc, LockC.NO);
            if (properties.getProperty("PDFasNewVersion").equalsIgnoreCase("false")) {
                deletOldVersion(Integer.toString(objId));
            }
        } catch (Exception ex) {
            logger.error("Cannot insert the converted document " + ex.getMessage());
        }
    }

    // Delet old version from document
    private static void deletOldVersion(String objId) {
        try {
            EditInfo edit = conn.ix().checkoutDoc( objId, "-1", EditInfoC.mbDocument, LockC.IF_FREE);
            Document docDel = edit.getDocument();
            DocVersion dvDel = docDel.getDocs()[1];
            dvDel.setDeleted(true);
            Document docDel2 = new Document();
            docDel2.setObjId(docDel.getObjId());
            docDel2.setDocs(new DocVersion[]{dvDel});
            conn.ix().checkinDocEnd( null, null, docDel2, LockC.YES);
        } catch (Exception ex) {
            logger.error("Cannot Lock the Object wiht ObjId " + objId + " " + ex.getMessage());
        }
    }

    // Invoked only from ConvertTiff2Pdf.java and calls the function readDoc, convert and insertDoc to replace or creat a PDF copy from a Tiff document  
    public static int convertTiff2Pdf(int objId, Logger log, Properties props, File docFile) {
        int numberOfPages = 0;

        properties = props;
        HashMap<String, Object> map = readDoc(objId);
        String absolutPathFromTiffFile = docFile.getAbsolutePath().toString();
        File pdfFile = Tiff2Pdf.convert(absolutPathFromTiffFile, absolutPathFromTiffFile.substring(0, absolutPathFromTiffFile.length() - 3) + "pdf");
        try {
            PdfReader reader = new PdfReader(pdfFile.getAbsolutePath().toString());
            numberOfPages = reader.getNumberOfPages();
        } catch (Exception ex) {
            logger.error("PDF number of pages cannot be identified : " + ex.getMessage());
        }
        insertDoc(pdfFile, ((Sord) map.get("sord")), objId);
        pdfFile.delete();
        return numberOfPages;
    }

    // Invoked only from SplitSortMerge.java and provides the barcode string form document 
    public static HashMap<String, String> getFieldsForMetaFile(int objId, Properties prop) {
        HashMap<String, String> map = new HashMap<String, String>();
        String fieldsForMetaFile = prop.getProperty("CreateTXT.FieldsForMetaFile","");
        Sord sord = (Sord) readDoc(objId).get("sord");
        ObjKey[] objKeys = sord.getObjKeys();
        while (fieldsForMetaFile.length() != 0) {
            String temp = "";
            if (fieldsForMetaFile.contains(",")) {
                temp = fieldsForMetaFile.substring(0, fieldsForMetaFile.indexOf(","));
                fieldsForMetaFile = fieldsForMetaFile.substring(fieldsForMetaFile.indexOf(",") + 1, fieldsForMetaFile.length());
            } else {
                temp = fieldsForMetaFile;
                fieldsForMetaFile = "";
            }
            for (int i = 0; i < objKeys.length; i++) {
                ObjKey objKey = objKeys[i];
                if (objKey.getName().equalsIgnoreCase(temp)) {
                    StringBuffer result = new StringBuffer();
                    for (int j = 0; j < objKey.getData().length; j++) {
                        result.append(objKey.getData()[j]);
                    }
                    map.put(temp, result.toString());
                    break;
                }
            }
        }
        return map;
    }

    public static HashMap<String, String> getSordInformation(int objId, Properties prop) {
        HashMap<String, String> map = new HashMap<String, String>();
        Sord sord = (Sord) readDoc(objId).get("sord");
        ObjKey[] objKeys = sord.getObjKeys();
        for (int i = 0; i < objKeys.length; i++) {
            ObjKey objKey = objKeys[i];
            if (objKey.getName().equalsIgnoreCase(prop.getProperty("BarcodeContent"))) {
                StringBuffer result = new StringBuffer();
                for (int j = 0; j < objKey.getData().length; j++) {
                    result.append("¶" + objKey.getData()[j]);
                }
                map.put(prop.getProperty("BarcodeContent"), result.toString());
            } else if (objKey.getName().toString().equalsIgnoreCase(prop.getProperty("ResultFieldPagecount"))) {
                StringBuffer result = new StringBuffer();
                for (int j = 0; j < objKey.getData().length; j++) {
                    result.append(objKey.getData()[j]);
                }
                map.put(prop.getProperty("ResultFieldPagecount"), result.toString());
            } else if (objKey.getName().toString().equalsIgnoreCase(prop.getProperty("NumberOfRecognizedBarcode"))) {
                StringBuffer result = new StringBuffer();
                for (int j = 0; j < objKey.getData().length; j++) {
                    result.append(objKey.getData()[j]);
                }
                map.put(prop.getProperty("NumberOfRecognizedBarcode"), result.toString());
            }
        }
        return map;
    }
}
