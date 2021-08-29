package de.di.barcodeserver.profiles;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;
import java.io.FileOutputStream;

/**
 *
 * @author Rahman
 */
public class Tiff2Pdf2 {

    public static int[] convert(String tif, String pdf) {
        int[] pages = new int[2];
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
                Rectangle pageSize = new Rectangle(image.getWidth(), image.getHeight());
                document.setPageSize(pageSize);
                document.newPage();
                document.add(image);
            }
            document.close();
            PdfReader read = new PdfReader(pdf);
            pages[0] = pagesTif;
            pages[1] = read.getNumberOfPages();
        } catch (Exception ex) {
            //do nothing
        }
        return pages;
    }
}