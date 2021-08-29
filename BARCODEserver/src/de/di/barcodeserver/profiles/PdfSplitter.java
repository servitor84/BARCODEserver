/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.di.barcodeserver.profiles;
import java.util.List;
//import org.apache.pdfbox.multipdf.Splitter;
//import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.Splitter;
/**
 *
 * @author ELO1
 */
public class PdfSplitter extends Splitter {
    public PdfSplitter(List<Integer> splitPages){
        _splitPages = splitPages;
    }
    
    private List<Integer> _splitPages;
    public boolean splitAtPage(int pageNumber){
        return _splitPages.contains(pageNumber);
    }
}
