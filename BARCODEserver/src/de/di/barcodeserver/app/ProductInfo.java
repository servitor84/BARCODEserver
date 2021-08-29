package de.di.barcodeserver.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emal
 */
public class ProductInfo {
    private String productname = "";
    private String programversion = "";
    private Properties prop = new Properties();
    
    public ProductInfo(){
        try {
            prop.load(getClass().getResourceAsStream("resources/barcodeserver_productinfo.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ProductInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        productname = prop.getProperty("product_name");
        programversion = prop.getProperty("prgram_version");
    }

    public String getProductname() {
        return productname;
    }

    public String getProgramversion() {
        return programversion;
    }
    
}
