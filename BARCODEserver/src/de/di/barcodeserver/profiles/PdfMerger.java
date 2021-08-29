/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.di.barcodeserver.profiles;

//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.commons.io.IOUtils;
//import org.apache.pdfbox.cos.COSArray;
//import org.apache.pdfbox.cos.COSDictionary;
//import org.apache.pdfbox.cos.COSInteger;
//import org.apache.pdfbox.cos.COSName;
//import org.apache.pdfbox.cos.COSNumber;
//import org.apache.pdfbox.cos.COSStream;
//import org.apache.pdfbox.exceptions.COSVisitorException;
//import org.apache.pdfbox.multipdf.LayerUtility;
////import org.apache.pdfbox.multipdf.PDFCloneUtility; - is private
//import org.apache.pdfbox.multipdf.PDFMergerUtility;
//import org.apache.pdfbox.multipdf.Splitter;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
//import org.apache.pdfbox.pdmodel.PDDocumentInformation;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
//import org.apache.pdfbox.pdmodel.PDPageTree;
//import org.apache.pdfbox.pdmodel.PDResources;
//import org.apache.pdfbox.pdmodel.common.PDStream;
//import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
//import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
//import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
//import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
//import org.apache.pdfbox.rendering.PDFRenderer;

/**
 *
 * @author ELO1
 */
public class PdfMerger {

    public static void main(String[] args) throws IOException {

        PDDocument source = PDDocument.load(new File("C:\\Users\\ELO1\\Desktop\\BarcodeServer Bug\\BSS055.pdf"));
        ArrayList<Integer> splits = new ArrayList<Integer>();
        splits.add(2);
        splits.add(100);
        PdfSplitter splitter = new PdfSplitter(splits);
        List<PDDocument> splitDocs = splitter.split(source);
        int i = 1;
        for (PDDocument doc : splitDocs) {
            File targetFile = new File("C:\\Users\\ELO1\\Desktop\\BarcodeServer Bug\\splits\\" + i + ".pdf");
            i++;
            try {
                doc.save(targetFile);
            } catch (COSVisitorException ex) {}
            doc.close();
        }

//PDPageTree pages = source.getDocumentCatalog().getPages();
//        for (int i = 0; i < 5; i++) {
//            PDDocument target = new PDDocument();
//            for (int j = i * 5; j < (i + 1) * 5; j++) {
//                LayerUtility layerUtility = new LayerUtility(target);
//                PDFormXObject form = layerUtility.importPageAsForm(source, j);
//                PDPage page = new PDPage();
//          
//                PDPageContentStream contents = new PDPageContentStream(target, page, AppendMode.APPEND, true);
//                contents.drawForm(form);
//                contents.close();
//                  target.addPage((PDPage)pages.get(j));
        //importedPage.setResources( ((PDPage)pages.get(j)).getResources() );
//            }
//          
//            File targetFile = new File("C:\\Users\\ELO1\\Desktop\\BarcodeServer Bug\\splits\\" + i + ".pdf");
//            target.save(targetFile);
//            target.close();
        //}
    }

}
