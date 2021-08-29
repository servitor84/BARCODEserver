package de.di.barcodeserver.profiles;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.LinkedList;
import java.io.OutputStream;
import java.io.FileInputStream;
import org.apache.log4j.Logger;
import java.io.FileOutputStream;
import org.apache.commons.lang3.*;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
//import org.apache.pdfbox.util.PDFMergerUtility;

/**
 *
 * @author Rahman
 */
public class SplitSortMergeSupporter {

    private File FILE;
    private Logger log;
    private int objectId;
    private int docNumber;
    private String pageCount;
    private Properties properties;
    private String barcodeContent;
    private String workingDirectory;
    private String mergingDirectory;
    private HashMap<String, String> fieldsForMetaFile;
    private ArrayList<String> deletabelFiles = new ArrayList<String>();
    private HashMap<String, String> sordInfo = new HashMap<String, String>();
    private HashMap<String, String> deletedPages = new HashMap<String, String>();

    public String getBarcodeContent() {
        return barcodeContent;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectIdTemp) {
        objectId = objectIdTemp;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties prop) {
        properties = prop;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCountTemp) {
        pageCount = pageCountTemp;
    }

    public File getFILE() {
        return FILE;
    }

    public void setFILE(File FILETEMP) {
        FILE = FILETEMP;
    }

    public ArrayList<String> getDeletabelFiles() {
        return deletabelFiles;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectoryTemp) {
        workingDirectory = workingDirectoryTemp;
    }

    public String getMergingDirectory() {
        return mergingDirectory;
    }

    public void setMergingDirectory(String mergingDirectoryTemp) {
        mergingDirectory = mergingDirectoryTemp;
    }

    private HashMap<String, String> getListOfPages(HashMap<Integer, String> saveAllPage) {
        String temp = "";
        HashMap<String, String> listOfPage = new HashMap<String, String>();
        HashMap<String, String> pageWithSimilarBarcode = new HashMap<String, String>();
        List<String> pageNumber = new ArrayList<String>();
        for (int i = 0; i < saveAllPage.size(); i++) {
            // Collect page, which has a simular barcode with more then one page  
            if (pageNumber.contains(saveAllPage.get(i).substring(0, saveAllPage.get(i).indexOf(";")))) {
                temp = pageWithSimilarBarcode.get(saveAllPage.get(i).substring(0, saveAllPage.get(i).indexOf(";")));
                temp += ";" + saveAllPage.get(i).substring(saveAllPage.get(i).indexOf(";") + 1, saveAllPage.get(i).length());
                pageWithSimilarBarcode.put(saveAllPage.get(i).substring(0, saveAllPage.get(i).indexOf(";")), temp);
            } else {
                try {
                    pageNumber.add(saveAllPage.get(i).substring(0, saveAllPage.get(i).indexOf(";")));
                    pageWithSimilarBarcode.put(saveAllPage.get(i).substring(0, saveAllPage.get(i).indexOf(";")), saveAllPage.get(i).substring(saveAllPage.get(i).indexOf(";") + 1, saveAllPage.get(i).length()));
                } catch (Exception ex) {
                    log.error("Cannot put the page with similar barcode to the hasmap " + ex.getMessage());
                }
            }
        }
        if (properties.containsKey("BarcodeMode") && properties.getProperty("BarcodeMode").equalsIgnoreCase("Complex")) {
            Iterator it = pageWithSimilarBarcode.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                String pages = sortByRealPageNumber(deletPagesWithSimilarBarcodeAndRealPageNumber(pairs.getKey() + ";" + pairs.getValue()));
                if (pages.contains(";")) {
                    listOfPage.put(pages.substring(0, pages.indexOf(";")), pages.substring(pages.indexOf(";") + 1, pages.length()));
                }
            }
            return listOfPage;
        } else {
            return pageWithSimilarBarcode;
        }
    }

    public String deletPagesWithSimilarBarcodeAndRealPageNumber(String que) {
        if (que.contains(";")) {
            String key = que.substring(0, que.indexOf(";")), endQue = "", tempQue;
            tempQue = que.substring(key.length() + 1);
            if (tempQue.contains(";")) {
                while (tempQue.contains(";")) {
                    if (getLogicalPageNumber(key).equalsIgnoreCase(getLogicalPageNumber(tempQue.substring(0, tempQue.indexOf(";"))))) {
                        log.info("Barcode " + getBarcode(key) + " found on page " + key + " and " + tempQue.substring(0, tempQue.indexOf(";")) + ", page " + tempQue.substring(0, tempQue.indexOf(";")) + " removed");
                        if (deletedPages.containsKey(getBarcode(key))) {
                            String pages = deletedPages.get(getBarcode(key)) + "," + tempQue.substring(0, tempQue.indexOf(";"));
                            deletedPages.put(getBarcode(key), pages);
                        } else {
                            deletedPages.put(getBarcode(key), tempQue.substring(0, tempQue.indexOf(";")));
                        }
                        deletabelFiles.add(constructString(tempQue.substring(0, tempQue.indexOf(";"))));
                        tempQue = tempQue.substring(tempQue.indexOf(";") + 1, tempQue.length());
                    } else {
                        if (endQue.isEmpty()) {
                            endQue += tempQue.substring(0, tempQue.indexOf(";"));
                        } else {
                            endQue += ";" + tempQue.substring(0, tempQue.indexOf(";"));
                        }
                        tempQue = tempQue.substring(tempQue.indexOf(";") + 1, tempQue.length());
                    }
                }
                if (getLogicalPageNumber(key).equalsIgnoreCase(getLogicalPageNumber(tempQue))) {
                    log.info("Barcode " + getBarcode(key) + " found on page " + key + " and " + tempQue + ", page " + tempQue + " removed");
                    if (deletedPages.containsKey(getBarcode(key))) {
                        String pages = deletedPages.get(getBarcode(key)) + "," + tempQue;
                        deletedPages.put(getBarcode(key), pages);
                    } else {
                        deletedPages.put(getBarcode(key), tempQue);
                    }
                    deletabelFiles.add(constructString(tempQue));
                    if (!endQue.isEmpty()) {
                        return key + ";" + deletPagesWithSimilarBarcodeAndRealPageNumber(endQue);
                    } else {
                        return key;
                    }
                } else {
                    if (!endQue.isEmpty()) {
                        return key + ";" + deletPagesWithSimilarBarcodeAndRealPageNumber(endQue + ";" + tempQue);
                    } else {
                        return key + ";" + deletPagesWithSimilarBarcodeAndRealPageNumber(tempQue);
                    }
                }
            } else {
                if (getLogicalPageNumber(key).equalsIgnoreCase(getLogicalPageNumber(tempQue))) {
                    log.info("Barcode " + getBarcode(key) + " found on page " + key + " and " + tempQue + ", page " + tempQue + " removed");
                    if (deletedPages.containsKey(getBarcode(key))) {
                        String pages = deletedPages.get(getBarcode(key)) + "," + tempQue;
                        deletedPages.put(getBarcode(key), pages);
                    } else {
                        deletedPages.put(getBarcode(key), tempQue);
                    }
                    deletabelFiles.add(constructString(tempQue));
                    return key;
                } else {
                    return key + ";" + tempQue;
                }
            }
        } else {
            return que;
        }
    }

    //If the BarcodeMode is complex, then the physical pagen number can be different from logical page number
    //sortByRealPageNumber will sort the collection of files with the aide of 
    public String sortByRealPageNumber(String physicalPageCollection) {
        if (!physicalPageCollection.contains(";")) {
            return physicalPageCollection;
        } else {
            int numberOfSimicolon = StringUtils.countMatches(physicalPageCollection, ";");
            int[] x = new int[numberOfSimicolon + 1];
            for (int j = 0; j < numberOfSimicolon + 1; j++) {
                if (physicalPageCollection.contains(";")) {
                    x[j] = Integer.valueOf(physicalPageCollection.substring(0, physicalPageCollection.indexOf(";")));
                    physicalPageCollection = physicalPageCollection.substring(physicalPageCollection.indexOf(";") + 1, physicalPageCollection.length());
                } else {
                    x[j] = Integer.valueOf(physicalPageCollection);
                    physicalPageCollection = "";
                }
            }
            // Bubbelsort algorithmus
            boolean unsortiert = true;
            int temp;

            while (unsortiert) {
                unsortiert = false;
                for (int i = 0; i < x.length - 1; i++) {
                    if (Integer.valueOf(getLogicalPageNumber(String.valueOf(x[i]))) > Integer.valueOf(getLogicalPageNumber(String.valueOf(x[i + 1])))) {
                        temp = x[i];
                        x[i] = x[i + 1];
                        x[i + 1] = temp;
                        unsortiert = true;
                    }
                }
            }

            for (int l = 0; l < x.length; l++) {
                physicalPageCollection += ";" + String.valueOf(x[l]);
            }
            return physicalPageCollection.substring(1);
        }
    }

    // Verified the barcodes from all pages to identify the pages with similar barcodes and insert them in a map, which will be returnd by this function
    public HashMap<Integer, String> findPageWithSimilarBarcode(String string) {
        String tempBarcode = "", barcodeString = string;
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        int z = 0;
        if (barcodeString.subSequence(0, 2).equals("¶¶")) {
            barcodeString = barcodeString.substring(1, barcodeString.length());
        }
        while (barcodeString.length() > 0) {
            if (barcodeString.contains("¶")) {
                barcodeString = barcodeString.substring(1);
                if (barcodeString.contains("¶")) {
                    tempBarcode = barcodeString.substring(0, barcodeString.indexOf("¶"));
                    barcodeString = barcodeString.substring(tempBarcode.length());
                } else {
                    tempBarcode = barcodeString;
                    barcodeString = "";
                }
            }
            map.put(z, tempBarcode);
            z++;
        }
        HashMap<Integer, String> saveAllPage = new HashMap<Integer, String>();
        int h = 0;
        String firstBarcode = "", secondBarcode = "", secondPageNumber = "";
        List<String> pageNumber = new ArrayList<String>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.size(); j++) {
                firstBarcode = map.get(i).substring(0, map.get(i).indexOf(";"));
                secondBarcode = map.get(j).substring(0, map.get(j).indexOf(";"));
                secondPageNumber = map.get(j).substring(secondBarcode.length() + 1, map.get(j).length());
                if (firstBarcode.equals(secondBarcode) && !pageNumber.contains(secondPageNumber)) {
                    pageNumber.add(secondPageNumber);
                    if (i != j) {
                        saveAllPage.put(h, map.get(i).substring(map.get(i).indexOf(";") + 1, map.get(i).length()) + ";" + map.get(j).substring(map.get(j).indexOf(";") + 1, map.get(j).length()));
                        h++;
                    }
                }
            }
        }
        return saveAllPage;
    }

    public String getLogicalPageNumber(String physicalPageNumber) {

        String logicalPageNumber = null, barcodes = getCorrectPilgrow(sordInfo.get(getProperties().getProperty("BarcodeContent")));
        for (int i = 1; i < Integer.parseInt(physicalPageNumber); i++) {
            barcodes = barcodes.substring(1);
            if (barcodes.contains("¶")) {
                barcodes = barcodes.substring(barcodes.indexOf("¶"));
            }
        }
        barcodes = barcodes.substring(barcodes.indexOf("-") + 1);
        logicalPageNumber = barcodes.substring(0, barcodes.indexOf(";"));
        return logicalPageNumber;
    }

    private void mergePagesWithSimilarBarcode(HashMap<String, String> pageWithSimilarBarcode) {
        if (properties.containsKey("BarcodeMode") && properties.getProperty("BarcodeMode").equalsIgnoreCase("complex")) {
            String temp = "", temp1;
            // Merging the pages with similar barcode
            for (String key : pageWithSimilarBarcode.keySet()) {
                temp = pageWithSimilarBarcode.get(key);
                temp1 = "";
                if (temp.contains(";")) {
                    while (!temp.isEmpty()) {
                        if (temp1.isEmpty()) {
                            mergeDoc(constructString(key), constructString(temp.substring(0, temp.indexOf(";"))), getWorkingDirectory() + key + "_" + temp.substring(0, temp.indexOf(";")) + ".pdf");
                            deletabelFiles.add(constructString(key));
                            deletabelFiles.add(constructString(temp.substring(0, temp.indexOf(";"))));
                            temp1 += key + "_" + temp.substring(0, temp.indexOf(";"));
                            temp = temp.substring(temp.indexOf(";") + 1, temp.length());
                        } else {
                            if (temp.contains(";")) {
                                mergeDoc(constructString(temp1), constructString(temp.substring(0, temp.indexOf(";"))), getWorkingDirectory() + temp1 + "_" + temp.substring(0, temp.indexOf(";")) + ".pdf");
                                deletabelFiles.add(constructString(temp1));
                                deletabelFiles.add(constructString(temp.substring(0, temp.indexOf(";"))));
                                temp1 += "_" + temp.substring(0, temp.indexOf(";"));
                                temp = temp.substring(temp.indexOf(";") + 1, temp.length());
                            } else {
                                mergeDoc(constructString(temp1), constructString(temp), getWorkingDirectory() + temp1 + "_" + temp + ".pdf");
                                deletabelFiles.add(constructString(temp1));
                                deletabelFiles.add(constructString(temp));
                                temp = "";
                            }
                        }
                    }
                } else {
                    if (!key.equalsIgnoreCase(temp)) {
                        mergeDoc(constructString(key), constructString(temp), getWorkingDirectory() + key + "_" + temp + ".pdf");
                        deletabelFiles.add(constructString(key));
                        deletabelFiles.add(constructString(temp));
                    }
                }
            }
            // Shift the result of merged pages from working directory to merging directory
            String prefix = "";
            if (getProperties().containsKey("SplitFilePrefix")) {
                prefix = getProperties().getProperty("SplitFilePrefix");
            }
            for (String key : pageWithSimilarBarcode.keySet()) {
                String barcode = getBarcode(key);
                String pageNumberInWorkingDirectory = key + "_" + pageWithSimilarBarcode.get(key).replace(';', '_');
                File mergedFile = new File(getWorkingDirectory() + pageNumberInWorkingDirectory + ".pdf");
                createTextFile(mergedFile, prefix, barcode);
                moveFile(getMergingDirectory() + prefix + getObjectId() + "-" + barcode + ".pdf", mergedFile);
                createSigFile(prefix, barcode);
            }
        } else {
            String tp1 = "", tp2 = "", temp = "";
            Iterator it = pageWithSimilarBarcode.entrySet().iterator();
            docNumber = 1;
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                temp = (String) pairs.getValue();
                tp1 = (String) pairs.getKey();
                while (!temp.isEmpty()) {
                    if (temp.contains(";")) {
                        tp2 = temp.substring(0, temp.indexOf(";"));
                        //System.out.println("Der tp2 hat den Wert " + tp2);
                        temp = temp.substring(tp2.length() + 1);
                    } else {
                        tp2 = temp;
                        temp = "";
                    }
                    mergeDoc(constructString(tp1), constructString(tp2), getWorkingDirectory() + tp1 + "_" + tp2 + ".pdf");
                    deletabelFiles.add(constructString(tp1));
                    deletabelFiles.add(constructString(tp2));
                    tp1 += "_" + tp2;
                }
                String prefix = "";
                if (getProperties().containsKey("SplitFilePrefix")) {
                    prefix = getProperties().getProperty("SplitFilePrefix");
                }
                File file = new File(getWorkingDirectory() + tp1 + ".pdf");
                if (getProperties().getProperty("CreateTXT").equalsIgnoreCase("true")) {
                    createTextFile(file, prefix, getBarcode(tp1.substring(0, tp1.indexOf("_"))));
                }
                moveFile(getMergingDirectory() + prefix + getObjectId() + "-" + getBarcode(tp1.substring(0, tp1.indexOf("_"))) + "-" + docNumber + ".pdf", file);
                if (getProperties().getProperty("CreateSIG").equalsIgnoreCase("true")) {
                    createSigFile(prefix, getBarcode(tp1.substring(0, tp1.indexOf("_"))));
                }
                docNumber++;
            }
        }
    }

    private void createTextFile(File file, String prefix, String barcode) {
        if (properties.getProperty("CreateTXT", "").equalsIgnoreCase("true")) {
            try {
                String textFileName = "";
                if (properties.containsKey("BarcodeMode") && properties.getProperty("BarcodeMode").equalsIgnoreCase("complex")) {
                    textFileName = getMergingDirectory() + prefix + getObjectId() + "-" + barcode + ".txt";
                } else {
                    textFileName = getMergingDirectory() + prefix + getObjectId() + "-" + barcode + "-" + docNumber + ".txt";
                }
                PrintWriter textFile = new PrintWriter(textFileName);
                if ( !getProperties().getProperty("CreateTXT.FieldsForMetaFile","").isEmpty()) {
                    Iterator it = fieldsForMetaFile.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pairs = (Map.Entry) it.next();
                        textFile.println("##" + pairs.getKey() + "=" + pairs.getValue() + "##");
                    }
                }

                PdfReader doc = new PdfReader(file.getAbsolutePath().toString());
                textFile.println("##PDFPAGECOUNT=" + doc.getNumberOfPages() + "##");
                textFile.println("##TEMPLATE=" + properties.getProperty("CreateTXT.XMLtemplate") + "##");
                textFile.println("##BC=" + barcode + "##");
                String pages = file.getName().substring(0, file.getName().length() - 4);
                String pagesBarcode = "", temp = "";
                while (pages.contains("_")) {
                    temp = pages.substring(0, pages.indexOf("_"));
                    pages = pages.substring(temp.length() + 1);
                    pagesBarcode += "¶" + getBarcodeWithPageNumber(temp) + ";" + temp;
                }
                pagesBarcode += "¶" + getBarcodeWithPageNumber(pages) + ";" + pages;
                textFile.println("##BCCONTENTSSM=" + pagesBarcode + "##");
                textFile.println("##PAGES=" + file.getName().substring(0, file.getName().length() - 4).replace("_", ",") + "##");
                Date zeitstempel = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                textFile.println("##DATE=" + simpleDateFormat.format(zeitstempel) + "##");
                textFile.println("##PAGESREMOVED=" + deletedPages.get(barcode) + "##");
                textFile.close();
            } catch (Exception ex) {
                log.error("Cannot create the correct text file for splitted pdf file " + ex.getMessage());
            }
        }
    }

    private void createSigFile(String prefix, String barcode) {
        if (properties.getProperty("CreateSIG", "").equalsIgnoreCase("true")) {
            String sigFileName = "";
            if (properties.containsKey("BarcodeMode") && properties.getProperty("BarcodeMode").equalsIgnoreCase("complex")) {
                sigFileName = getMergingDirectory() + prefix + getObjectId() + "-" + barcode + ".sig";
            } else {
                sigFileName = getMergingDirectory() + prefix + getObjectId() + "-" + barcode + "-" + docNumber + ".sig";
            }
            try {
                PrintWriter sigFile = new PrintWriter(sigFileName, "UTF-8");
                sigFile.println("");
                sigFile.close();
            } catch (Exception ex) {
                log.error("Cannot create SIG file for splitted PDF file " + ex.getMessage());
            }
        }
    }

    // Displays the associated barcode with real pagenumber from document on the basis of physical pagenumber
    private String getBarcodeWithPageNumber(String str) {
        String tempBarcode = getCorrectPilgrow(sordInfo.get(getProperties().getProperty("BarcodeContent")));
        tempBarcode = tempBarcode.substring(1, tempBarcode.length());
        for (int i = 1; i < Integer.parseInt(str); i++) {
            if (tempBarcode.contains("¶")) {
                tempBarcode = tempBarcode.substring(tempBarcode.indexOf("¶") + 1, tempBarcode.length());
            }
        }
        String barcode = tempBarcode.substring(0, tempBarcode.indexOf(";"));
        return barcode;

    }

    // Displays the associated barcode from document on the basis of physical pagenumber
    private String getBarcode(String str) {

        String tempBarcode = getCorrectPilgrow(sordInfo.get(getProperties().getProperty("BarcodeContent")));
        tempBarcode = tempBarcode.substring(1, tempBarcode.length());
        for (int i = 1; i < Integer.parseInt(str); i++) {
            if (tempBarcode.contains("¶")) {
                tempBarcode = tempBarcode.substring(tempBarcode.indexOf("¶") + 1, tempBarcode.length());
            }
        }
        String barcode = tempBarcode.substring(0, tempBarcode.indexOf(";"));
        return barcode.substring(Integer.parseInt(getProperties().getProperty("DropFirstCharactersCount","0")),
                barcode.length() - Integer.parseInt(getProperties().getProperty("DropLastCharactersCount")));
    }

    public boolean moveFile(String target, File source) {
        File temp = new File(target);
        if (temp.exists()) {
            temp.delete();
        }
        return source.renameTo(temp);
    }

    private void deletWorkingDirectory(File file) throws IOException {
        if (file.isDirectory()) {
            if (file.list().length == 0) {
                file.delete();
            } else {
                String files[] = file.list();
                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    deletWorkingDirectory(fileDelete);
                }
                if (file.list().length == 0) {
                    file.delete();
                }
            }
        } else {
            file.delete();
        }
    }

    private String constructString(String physicalPageNumber) {
        String filePath = workingDirectory;
        filePath += physicalPageNumber + ".pdf";
        return filePath;
    }

    private void generateFiles(File inputFile, String barcodeString, String workingFolder) {

        try {
            PdfReader pdfReader = new PdfReader(inputFile.getAbsolutePath());
        } catch (IOException ex) {
            //TODO: log exception
        }

    }

    // Split the inputFile in single pages and use the splitPDF
    private void splitFile(File inputFile, String workingFolder) {
        PdfReader reader;
        try {
            reader = new PdfReader(inputFile.getAbsolutePath().toString());
            InputStream inputStream;
            FileOutputStream outputStream;
            for (int i = 1; i < reader.getNumberOfPages() + 1; i++) {
                outputStream = new FileOutputStream(workingFolder + i + ".pdf");
                inputStream = new FileInputStream(FILE.getAbsolutePath().toString());
                splitPDF(inputStream, outputStream, i, i);
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }
            reader.close();
            inputFile.setReadable(true);
            inputFile.setWritable(true);
            if (inputFile.delete()) {
                log.info("File " + inputFile.getName() + " is now deleted from " + inputFile.getAbsolutePath().toString().substring(0, inputFile.getAbsolutePath().length() - inputFile.getName().length()));
            } else {
                log.info("Cannot delete file : " + inputFile.getName() + "  from : " + inputFile.getAbsolutePath().toString().substring(0, inputFile.getAbsolutePath().length() - inputFile.getName().length()));
            }
        } catch (Exception ex) {
            log.error("Cannot split the file " + ex.getMessage());
        }
    }

    // Split the PDF-file in predetermined pages
    private void splitPDF(InputStream inputStream, OutputStream outputStream, int fromPage, int toPage) {
        Document document = null;
        try {
            PdfReader inputPDF = new PdfReader(inputStream);
            int totalPages = inputPDF.getNumberOfPages();
            //make fromPage equals to toPage if it is greater
            if (fromPage > toPage) {
                fromPage = toPage;
            }
            if (toPage > totalPages) {
                toPage = totalPages;
            }
            // Create a writer for the outputstream
            document = new Document(inputPDF.getPageSizeWithRotation(1));
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data
            PdfImportedPage page;
            while (fromPage <= toPage) {
                page = writer.getImportedPage(inputPDF, fromPage);
                document.newPage();
                cb.addTemplate(page, 0, 0);
                fromPage++;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            log.error("Itext library cannot split the pdf file " + e.getMessage());
        } finally {
            if (document.isOpen()) {
                document.close();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ioe) {
                log.error("Cannot close the OutputStream after spliting the pdf file " + ioe.getMessage());
            }
        }
    }

    private void mergeDoc(String pdf_1, String pdf_2, String result) {
//        PDFMergerUtility ut = new PDFMergerUtility();
//        ut.addSource(new File(pdf_1));
//        ut.addSource(new File(pdf_2));
//
//        ut.setDestinationFileName(result);
//        try {
//            ut.mergeDocuments();
//        } catch (Exception ex) {
//            log.error("The document:" + pdf_1 + " and document: " + pdf_2 + " cannot be merged " + ex.getMessage());
//        }
    }

//    private void mergeDoc(List<String> pdfNames, String result) {
//        if (pdfNames != null && result != null && !result.isEmpty()) {
//            PDFMergerUtility ut = new PDFMergerUtility();
//            for (String pdfName : pdfNames) {
//                ut.addSource(new File(pdfName));
//            }
//            ut.setDestinationFileName(result);
//            try {
//                ut.mergeDocuments();
//            } catch (Exception ex) {
//                log.error("The documents could not be merged");
//                log.error(ex, ex);
//            }
//        }
//
//    }
    // Delet the workingdirectory and shift the document in mergingdirectory, wich hasn't similar barcode with another document
    // furthermore the copy of original document from archive will be deleted from temp folder of tomcat
    private void shiftAndDelet() {
        for (int i = 0; i < deletabelFiles.size(); i++) {
            new File(deletabelFiles.get(i)).delete();
        }
        File folder = new File(getWorkingDirectory());
        for (final File fileEntry : folder.listFiles()) {
            try {
                String prefix = "";
                if (getProperties().containsKey("SplitFilePrefix")) {
                    prefix = getProperties().getProperty("SplitFilePrefix");
                }
                if (properties.containsKey("BarcodeMode") && properties.getProperty("BarcodeMode").equalsIgnoreCase("complex")) {
                    createTextFile(fileEntry, prefix, replaceSpecialCharacter(getBarcode(fileEntry.getName().substring(0, fileEntry.getName().length() - 4))));
                    fileEntry.renameTo(new File(getMergingDirectory() + prefix + getObjectId() + "-" + replaceSpecialCharacter(getBarcode(fileEntry.getName().substring(0, fileEntry.getName().indexOf("."))) + ".pdf")));
                    createSigFile(prefix, replaceSpecialCharacter(getBarcode(fileEntry.getName().substring(0, fileEntry.getName().length() - 4))));
                } else {
                    createTextFile(fileEntry, prefix, replaceSpecialCharacter(getBarcode(fileEntry.getName().substring(0, fileEntry.getName().length() - 4))));
                    fileEntry.renameTo(new File(getMergingDirectory() + prefix + getObjectId() + "-" + replaceSpecialCharacter(getBarcode(fileEntry.getName().substring(0, fileEntry.getName().indexOf(".")))) + "-" + docNumber + ".pdf"));
                    createSigFile(prefix, replaceSpecialCharacter(getBarcode(fileEntry.getName().substring(0, fileEntry.getName().length() - 4))));
                    docNumber++;
                }
            } catch (Exception ex) {
                log.error("Cannot shift the file to the merging directory " + ex.getMessage());
            }
        }
        try {
            deletWorkingDirectory(new File(getWorkingDirectory()));
            FILE.delete();
            log.info("Working directory has been successfully deleted");
        } catch (Exception ex) {
            log.info("Cannot delete the working directory " + getWorkingDirectory() + " or working file at " + FILE.getAbsoluteFile().toString() + " because " + ex.getMessage());
        }
        File tempDirectory = new File(properties.getProperty("TempDirectory"));
        try {
            deleteFolder(tempDirectory);
            log.info("Temp directory has been successfully deleted");
        } catch (Exception ex1) {
            log.info("Cannot delete the temp directory " + tempDirectory.getAbsolutePath() + " because " + ex1.getMessage());
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

    // Shape the bacode string to the form : ¶ barcode ; page number 
    private String getCorrectPilgrow(String barcodes) {
        if (barcodes.isEmpty()) {
            return barcodes;
        }

        if (barcodes.startsWith("¶¶")) {
            barcodes = barcodes.substring(1, barcodes.length());
        }
        if (barcodes.substring(0, barcodes.length() - 1).equals("¶")) {
            barcodes = barcodes.substring(0, barcodes.length() - 1);
        }
        if (!barcodes.substring(0, 1).equals("¶")) {
            barcodes = "¶" + barcodes;
        }
        return barcodes;
    }

    // Convert the page number '1' to '01'
    // doesn't seem to do nothing
    private String makeDoubleDigitPageNumber(String barcodes) {
        String newBarcodesContent = "", temp = "";
        while (barcodes.contains("¶")) {
            barcodes = barcodes.substring(1);
            if (barcodes.contains("¶")) {
                temp = barcodes.substring(0, barcodes.indexOf("¶"));
            } else {
                temp = barcodes;
            }
            newBarcodesContent += "¶" + temp.substring(0, temp.indexOf("-") + 1);
            temp = temp.substring(temp.indexOf("-") + 1, temp.length());
            newBarcodesContent += temp.substring(0, temp.indexOf("-") + 1);
            temp = temp.substring(temp.indexOf("-") + 1);
            if (temp.substring(0, temp.indexOf(";")).length() == 1) {
                temp = "0" + temp;
            }
            newBarcodesContent += temp;
            if (barcodes.contains("¶")) {
                barcodes = barcodes.substring(barcodes.indexOf("¶"));
            } else {
                barcodes = "";
            }
            temp = "";
        }
//        log.info("BCCONTENTSSM = " + newBarcodesContent);
        return newBarcodesContent;
    }

    // Adjust the barcode string in wich not each page has a barcode
    private String adjustBarcode(String bcContent) {
        String adjustedBarcode = "", barcodes = bcContent;
        boolean firstRun = true;
        ArrayList<String[]> barcodeList = new ArrayList<String[]>();
        String unadjustedBarcode = "", realBarcode = "", physicalPageNumber = "", realPageNumber = "",
                previousRealBarcode = "", previousPhysicalPageNumber = "", previousRealPageNumber = "";
        while (barcodes.length() != 0) {
            barcodes = barcodes.substring(1);
            unadjustedBarcode = barcodes.substring(1, barcodes.indexOf(";"));
            if (barcodes.contains("¶")) {
                physicalPageNumber = barcodes.substring(barcodes.indexOf(";") + 1, barcodes.indexOf("¶"));
                barcodes = barcodes.substring(barcodes.indexOf("¶"), barcodes.length());
            } else {
                physicalPageNumber = barcodes.substring(barcodes.indexOf(";") + 1, barcodes.length());
                barcodes = "";
            }
            if (properties.containsKey("DropLastCharactersCount") && properties.getProperty("DropLastCharactersCount") != "" && !(properties.getProperty("DropLastCharactersCount").equals("0"))) {
                int dropLastCharactersCount = Integer.parseInt(properties.getProperty("DropLastCharactersCount"));
                int sortLastCharactersCount = Integer.parseInt(properties.getProperty("SortLastCharactersCount"));
                realBarcode = unadjustedBarcode.substring(0, unadjustedBarcode.length() - dropLastCharactersCount);
                realPageNumber = unadjustedBarcode.substring(unadjustedBarcode.length() - sortLastCharactersCount);
            } else {
                realPageNumber = unadjustedBarcode.substring(unadjustedBarcode.length() - 1);
                realBarcode = unadjustedBarcode.substring(0, unadjustedBarcode.length() - 1);
            }

            if (!firstRun && Integer.parseInt(previousPhysicalPageNumber) < Integer.parseInt(physicalPageNumber) - 1) {
                while (Integer.parseInt(previousPhysicalPageNumber) < Integer.parseInt(physicalPageNumber) - 1) {
                    int i = Integer.parseInt(previousPhysicalPageNumber) + 1;
                    previousPhysicalPageNumber = String.valueOf(i);
                    String[] temp = {previousRealBarcode, previousRealPageNumber, previousPhysicalPageNumber};
                    barcodeList.add(temp);
                }
            }
            String[] temp = {realBarcode, realPageNumber, physicalPageNumber};
            barcodeList.add(temp);
            previousRealBarcode = realBarcode;
            previousRealPageNumber = realPageNumber;
            previousPhysicalPageNumber = physicalPageNumber;
            firstRun = false;
        }
        // Wirte barcode, real page number and physical page number for the last pages of document, which has not barcodes 
        int numberOfRecgnisedBarcode = StringUtils.countMatches(bcContent, ";");
        if (numberOfRecgnisedBarcode < Integer.parseInt(pageCount)) {
            int difference = Integer.parseInt(pageCount) - numberOfRecgnisedBarcode;
            for (int i = 0; i < difference; i++) {
                realPageNumber = String.valueOf(Integer.parseInt(realPageNumber) + 1);
                physicalPageNumber = String.valueOf(Integer.parseInt(physicalPageNumber) + 1);
                String[] temp = {realBarcode, realPageNumber, physicalPageNumber};
                barcodeList.add(temp);
            }
        }
        //setBarcodeContent("");
        for (int i = 0; i < barcodeList.size(); i++) {
            barcodeContent += "¶" + barcodeList.get(i)[0] + "-" + barcodeList.get(i)[1] + ";" + barcodeList.get(i)[2];
        }
        for (int j = 0; j < barcodeList.size(); j++) {
            adjustedBarcode += "¶" + barcodeList.get(j)[0] + ";" + barcodeList.get(j)[2];
        }
        return adjustedBarcode;
    }

    private String fillPageWithoutBarcode(String barcodes, int numberOfPage) {
        String barcodeWithoutPageNumber = "", resultBarcodes = "";
        resultBarcodes = pageOneIsWithoutBarcode(barcodes, numberOfPage);
        int pageNumber1, pageNumber2;
        while (!barcodes.isEmpty()) {
            if (barcodes.contains("¶")) {
                if (barcodes.substring(0, 1).equals("¶")) {
                    barcodes = barcodes.substring(1);
                }

                if (barcodes.contains("¶")) {
                    resultBarcodes += "¶" + barcodes.substring(0, barcodes.indexOf("¶"));
                    barcodeWithoutPageNumber = barcodes.substring(0, barcodes.indexOf(";"));
                    pageNumber1 = Integer.valueOf(barcodes.substring(barcodes.indexOf(";") + 1, barcodes.indexOf("¶")));
                    barcodes = barcodes.substring(barcodes.indexOf("¶") + 1);
                    if (barcodes.contains("¶")) {
                        pageNumber2 = Integer.valueOf(barcodes.substring(barcodes.indexOf(";") + 1, barcodes.indexOf("¶")));
                    } else {
                        pageNumber2 = Integer.valueOf(barcodes.substring(barcodes.indexOf(";") + 1, barcodes.length()));
                    }
                } else {
                    resultBarcodes += "¶" + barcodes;
                    barcodeWithoutPageNumber = barcodes.substring(0, barcodes.indexOf(";"));
                    pageNumber1 = Integer.valueOf(barcodes.substring(barcodes.indexOf(";") + 1, barcodes.length()));
                    pageNumber2 = numberOfPage;
                    barcodes = "";
                }
                if (pageNumber2 == numberOfPage && pageNumber1 == 1) {
                    if (pageNumber2 > pageNumber1) {
                        int diffPageNumber = pageNumber2 - pageNumber1;
                        for (int i = 0; i < diffPageNumber; i++) {
                            resultBarcodes += "¶" + barcodeWithoutPageNumber + ";" + (pageNumber1 + i + 1);
                        }
                    }
                } else if (pageNumber2 == numberOfPage) {
                    if (pageNumber2 - 1 > pageNumber1) {
                        int diffPageNumber = pageNumber2 - pageNumber1;
                        for (int i = 0; i < diffPageNumber - 1; i++) {
                            resultBarcodes += "¶" + barcodeWithoutPageNumber + ";" + (pageNumber1 + i + 1);
                        }
                    }
                } else {
                    if (pageNumber2 - 1 > pageNumber1) {
                        int diffPageNumber = pageNumber2 - pageNumber1;
                        for (int i = 0; i < diffPageNumber - 1; i++) {
                            resultBarcodes += "¶" + barcodeWithoutPageNumber + ";" + (pageNumber1 + i + 1);
                        }
                    }
                }

            } else {
                pageNumber1 = Integer.valueOf(barcodes.substring(barcodes.indexOf(";") + 1, barcodes.length()));
                barcodeWithoutPageNumber = barcodes.substring(0, barcodes.indexOf(";"));
                if (pageNumber1 <= numberOfPage) {
                    for (int j = 0; j < (numberOfPage + 1 - pageNumber1); j++) {
                        resultBarcodes += "¶" + barcodeWithoutPageNumber + ";" + (pageNumber1 + j);
                    }
                }
                barcodes = "";
            }

        }
        log.info("BCCONTENTSSM = " + resultBarcodes);
        return resultBarcodes;
    }

    // Creat the working and merging directories and calls the methodes splitFile, mergePagesWithSimilarBarcode and deletAndShift
    public void run(HashMap<String, String> mapForMetaFile, HashMap<String, String> sordInformation, String filePath, int objId, Properties prop, Logger logger) {
        log = logger;
        fieldsForMetaFile = mapForMetaFile;
        setObjectId(objId);
        setFILE(new File(filePath));
        setProperties(prop);
        setWorkingDirectory(prop.getProperty("WorkingDirectory") + "\\");
        setMergingDirectory(prop.getProperty("OutputDirectory") + "\\");
        new File(getWorkingDirectory()).mkdir();
        new File(getMergingDirectory()).mkdir();
        splitFile(getFILE(), getWorkingDirectory());
        if (prop.getProperty("BarcodeMode").equalsIgnoreCase("Complex")) {
            //
            sordInfo.put(prop.getProperty("BarcodeContent"), makeDoubleDigitPageNumber(getCorrectPilgrow(sordInformation.get(prop.getProperty("BarcodeContent")))));
            sordInfo.put(prop.getProperty("ResultFieldPagecount"), sordInformation.get(prop.getProperty("ResultFieldPagecount")));
            sordInfo.put(prop.getProperty("NumberOfRecognizedBarcode"), sordInformation.get(prop.getProperty("NumberOfRecognizedBarcode")));
            setPageCount(sordInfo.get(prop.getProperty("ResultFieldPagecount")));
            barcodeContent = sordInfo.get(prop.getProperty("BarcodeContent"));
            mergePagesWithSimilarBarcode(getListOfPages(findPageWithSimilarBarcode(adjustBarcode(getBarcodeContent()))));
        } else {
            sordInfo.put(prop.getProperty("BarcodeContent"), fillPageWithoutBarcode(getCorrectPilgrow(sordInformation.get(prop.getProperty("BarcodeContent"))), Integer.valueOf(sordInformation.get(prop.getProperty("ResultFieldPagecount")))));
            sordInfo.put(prop.getProperty("ResultFieldPagecount"), sordInformation.get(prop.getProperty("ResultFieldPagecount")));
            sordInfo.put(prop.getProperty("NumberOfRecognizedBarcode"), sordInformation.get(prop.getProperty("NumberOfRecognizedBarcode")));
            setPageCount(sordInformation.get(prop.getProperty("ResultFieldPagecount")));
            barcodeContent = sordInfo.get(prop.getProperty("BarcodeContent"));
            mergePagesWithSimilarBarcode(getListOfPages(findPageWithSimilarBarcode(getBarcodeContent())));
        }
        shiftAndDelet();
    }

    public String[] mergeArcWfDoc(String wfDocPath, String wfBcContent, String arcDocPath, String arcBcContent, String propertiesPath) {
        String[] result = new String[5];
        try {
            result[4] = "";
            Properties prop = new Properties();
            try {
                prop.load(new FileInputStream(propertiesPath));
                setProperties(prop);
            } catch (Exception ex) {
                result[4] += "Property file cannot be load" + ex.getMessage();
            }
            setWorkingDirectory(prop.getProperty("WorkingDirectory") + "\\");
            setMergingDirectory(prop.getProperty("OutputDirectory") + "\\");
            try {
                new File(getWorkingDirectory()).mkdir();
            } catch (Exception ex) {
                result[4] += "Cannot create the directory" + getWorkingDirectory() + " because " + ex.getMessage() + "\n";
            }
            try {
                new File(getMergingDirectory()).mkdir();
            } catch (Exception ex) {
                result[4] += "Cannot create the directory" + getMergingDirectory() + " because " + ex.getMessage() + "\n";
            }
            //Spliting the arc and wf documents 
            try {
                sordInfo.put(prop.getProperty("BarcodeContent"), arcBcContent);
                splitFileELOas(new File(arcDocPath), getWorkingDirectory(), arcBcContent);
                sordInfo.put(prop.getProperty("BarcodeContent"), wfBcContent);
                splitFileELOas(new File(wfDocPath), getWorkingDirectory(), wfBcContent);
            } catch (Exception ex) {
                if (wfBcContent.isEmpty()) {
                    result[4] += "Cannot split the " + wfDocPath + " because the BCCONTENTSSM is empty";
                } else if (arcBcContent.isEmpty()) {
                    result[4] += "Cannot split the " + arcDocPath + " because the BCCONTENTSSM is empty";
                } else {
                    result[4] += "Cannot split the document " + wfDocPath + " or " + arcDocPath + " because of " + ex.getMessage() + "\n";
                }
            }

            //The pages are available in LinkedList and sorted by page number        
            String temp = "";
            boolean firstRun = true;
            LinkedList<File> files = (LinkedList) FileUtils.listFiles(new File(getWorkingDirectory()), null, true);
            if (files.size() == 1) {
                temp = files.get(0).getAbsolutePath().toString();
                result[3] = "01";
            } else {
                for (int i = 0; i < files.size(); i++) {
                    String file1 = "", file2 = "";
                    if (firstRun) {
                        try {
                            file1 = files.get(i).getName().toString();
                            if (files.size() > i + 1) {
                                file2 = files.get(i + 1).getName().toString();
                            }
                            mergeDoc(files.get(i).getAbsolutePath().toString(), files.get(i + 1).getAbsolutePath().toString(), getWorkingDirectory()
                                    + files.get(i).getName().toString().substring(0, files.get(i).getName().toString().indexOf("."))
                                    + "_" + files.get(i + 1).getName().toString());
                            temp = getWorkingDirectory() + files.get(i).getName().toString().substring(0, files.get(i).getName().toString().indexOf(".")) + "_" + files.get(i + 1).getName().toString();
                        } catch (Exception ex) {
                            result[4] += "Cannot merge document : " + file1 + " with " + file2 + " " + ex.getMessage() + "\n";
                        }
                        firstRun = false;
                        i++;
                    } else {
                        try {
                            file1 = files.get(i).getName().toString();
                            if (files.size() > i + 1) {
                                file2 = files.get(i + 1).getName().toString();
                            }
                            mergeDoc(temp, files.get(i).getAbsolutePath().toString(), getWorkingDirectory() + temp.substring(getWorkingDirectory().length(), temp.indexOf(".")) + "_"
                                    + files.get(i).getAbsolutePath().toString().substring(getWorkingDirectory().length()));
                            temp = getWorkingDirectory() + temp.substring(getWorkingDirectory().length(), temp.indexOf(".")) + "_"
                                    + files.get(i).getAbsolutePath().toString().substring(getWorkingDirectory().length());
                        } catch (Exception ex) {
                            result[4] += "Cannot merge document : " + file1 + " with " + file2 + " " + ex.getMessage() + "\n";
                        }
                    }
                }
                try {
                    result[3] = temp.substring(getWorkingDirectory().length(), temp.indexOf(".")).replace("_", ",");
                } catch (Exception ex) {
                    result[4] += "Cannot calculate page number " + ex.getMessage() + "\n";
                }
            }
            //Investigate the BCCONTENT 
            String var = getBarcode("1"), bcContent = "";
            for (int i = 0; i < files.size(); i++) {
                bcContent += "¶" + var + "-" + files.get(i).getName().toString().substring(0, files.get(i).getName().toString().indexOf(".")) + ";" + String.valueOf(i + 1);
            }
            result[1] = bcContent;
            //Investigate the number of pages
            try {
                PdfReader reader = new PdfReader(temp);
                result[2] = String.valueOf(reader.getNumberOfPages());
            } catch (Exception ex) {
                result[4] += "Cannot identify the number of pages " + ex.getMessage() + "\n";
            }
            //Shift the merging result file to output directory
            File mergedFile = new File(temp);
            try {
                moveFile(getMergingDirectory() + getProperties().getProperty("SplitFilePrefix", "Split") + "_" + getBarcode("1") + ".pdf", mergedFile);
            } catch (Exception ex) {
                result[4] += "Cannot move document : " + mergedFile.getName().toString() + " to " + getMergingDirectory() + " because of " + ex.getMessage() + "\n";
            }
            result[0] = getMergingDirectory() + getProperties().getProperty("SplitFilePrefix", "Split") + "_" + getBarcode("1") + ".pdf";

            //Delet the working directroy
            try {
                deletWorkingDirectory(new File(getWorkingDirectory()));
            } catch (IOException ioex) {
                result[4] += "Cannot delete the working directory " + ioex.getMessage() + "\n";
            }
            return result;
        } catch (Exception endEx) {
            result[4] += "SSM throw Exception " + endEx.getMessage() + "\n";
            //Delet the working directroy
            try {
                deletWorkingDirectory(new File(getWorkingDirectory()));
            } catch (IOException ioex) {
                result[4] += "Cannot delete the working directory " + ioex.getMessage() + "\n";
            }
            return result;
        }
    }

    private String splitFileELOas(File inputFile, String workingFolder, String barcode) {
        String result = "";
        try {
            PdfReader reader = new PdfReader(new FileInputStream(new File(inputFile.getAbsolutePath().toString())));
            for (int i = 1; i < reader.getNumberOfPages() + 1; i++) {
                splitPDF(new FileInputStream(inputFile.getAbsolutePath().toString()), new FileOutputStream(workingFolder + getLogicalPageNumber(String.valueOf(i)) + ".pdf"), i, i);
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return result;
    }

    private String replaceSpecialCharacter(String barcode) {
        String barcodeWithoutSpecialCharacter = barcode;
        while (barcodeWithoutSpecialCharacter.contains("\\")) {
            barcodeWithoutSpecialCharacter = barcodeWithoutSpecialCharacter.replace('\\', '=');
        }
        while (barcodeWithoutSpecialCharacter.contains("/")) {
            barcodeWithoutSpecialCharacter = barcodeWithoutSpecialCharacter.replace('/', '=');
        }
        while (barcodeWithoutSpecialCharacter.contains(":")) {
            barcodeWithoutSpecialCharacter = barcodeWithoutSpecialCharacter.replace(':', '=');
        }
        while (barcodeWithoutSpecialCharacter.contains("*")) {
            barcodeWithoutSpecialCharacter = barcodeWithoutSpecialCharacter.replace('*', '=');
        }
        while (barcodeWithoutSpecialCharacter.contains("?")) {
            barcodeWithoutSpecialCharacter = barcodeWithoutSpecialCharacter.replace('?', '=');
        }
        while (barcodeWithoutSpecialCharacter.contains("\"")) {
            barcodeWithoutSpecialCharacter = barcodeWithoutSpecialCharacter.replace('"', '=');
        }
        while (barcodeWithoutSpecialCharacter.contains("<")) {
            barcodeWithoutSpecialCharacter = barcodeWithoutSpecialCharacter.replace('<', '=');
        }
        while (barcodeWithoutSpecialCharacter.contains(">")) {
            barcodeWithoutSpecialCharacter = barcodeWithoutSpecialCharacter.replace('>', '=');
        }
        while (barcodeWithoutSpecialCharacter.contains("|")) {
            barcodeWithoutSpecialCharacter = barcodeWithoutSpecialCharacter.replace('|', '=');
        }
        return barcodeWithoutSpecialCharacter;
    }

    public String pageOneIsWithoutBarcode(String barcodes, int pageNumber) {
        String resultBarcodes = "";
        // Wenn auf keine Seite ein Barcode gefunden wurde
        if (barcodes.isEmpty()) {
            for (int i = 1; i < pageNumber + 1; i++) {
                resultBarcodes = resultBarcodes + "¶BCnotFound-" + i + ";" + i;
            }
            return resultBarcodes;
        }
        // Wenn nur auf die erste Seite kein Barcode gefunden wurde
        if (barcodes.contains("¶")) {
            if (barcodes.substring(0, 1).equals("¶")) {
                barcodes = barcodes.substring(1);
            }
            int pageNumber1;
            if (barcodes.contains("¶")) {
                pageNumber1 = Integer.valueOf(barcodes.substring(barcodes.indexOf(";") + 1, barcodes.indexOf("¶")));
            } else {
                pageNumber1 = Integer.valueOf(barcodes.substring(barcodes.indexOf(";") + 1, barcodes.length()));
            }
            if (pageNumber1 > 1) {
                for (int i = 1; i < pageNumber1; i++) {
                    resultBarcodes = resultBarcodes + "¶BCnotFound-" + i + ";" + i;
                }
            }
            return resultBarcodes;
        } else {
            if (barcodes.contains(";")) {
                int pageNumber1 = Integer.valueOf(barcodes.substring(barcodes.indexOf(";") + 1, barcodes.length()));
                if (pageNumber1 > 1) {
                    for (int i = 1; i < pageNumber1; i++) {
                        resultBarcodes = resultBarcodes + "¶BCnotFound-" + i + ";" + i;
                    }
                }
                return resultBarcodes;
            }
        }
        return resultBarcodes;
    }

    public static void main(String[] args) {

        String bcsString = "¶00011000030744;1¶00011000030808;2¶00011000031884;3¶00011000032220;4¶00011000032221;5¶00011000032314;6¶00011000032387;7¶00011000032704;8¶00011000032929;9¶00011000033008;10¶00011000033140;11¶00011000030744;12¶00011000030808;13¶00011000031884;14¶00011000032220;15¶00011000032221;16¶00011000032314;17¶00011000032387;18¶00011000032704;19¶00011000032704;20¶00011000032704;21¶00011000032929;22¶00011000033008;23¶00011000033140;25";
        SplitSortMergeSupporter ssms = new SplitSortMergeSupporter();

        Properties properties = new Properties();

        ssms.setProperties(properties);
        HashMap<String, String> mapa = ssms.getListOfPages(ssms.findPageWithSimilarBarcode(bcsString));
        ssms.makeDoubleDigitPageNumber(bcsString);
        ssms.pageCount = "25";
        ssms.adjustBarcode(bcsString);

        System.out.println("ceva");

    }
}
