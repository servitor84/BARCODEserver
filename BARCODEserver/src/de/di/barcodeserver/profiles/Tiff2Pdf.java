package de.di.barcodeserver.profiles;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author Rahman
 */
public class Tiff2Pdf {

    public static File convert(String tif, String pdf) {
        File pdfFile = null;
        String imgeFilename = tif;
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(
                    document,
                    new FileOutputStream(pdf));
            writer.setStrictImageSequence(true);
            Image image;
            document.open();
            RandomAccessFileOrArray ra = new RandomAccessFileOrArray(imgeFilename);
            int pagesTif = TiffImage.getNumberOfPages(ra);
            for (int i = 1; i <= pagesTif; i++) {
                image = TiffImage.getTiffImage(ra, i);
                image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                document.setMargins(0, 0, 0, 0);
                document.newPage();
                document.add(image);
            }
            pdfFile = new File(pdf);
            ra.close();
            document.close();
        } catch (Exception ex) {
            //do nothing
        }
        return pdfFile;
    }
}