package de.di.barcodeserver.web;



import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import de.di.barcodeserver.barcode.BarcodeProcessor;
import de.di.barcodeserver.barcode.Info;
import java.io.File;
import java.io.FileInputStream;
import javax.servlet.ServletContext;
import java.util.logging.Level;
import java.util.*;

public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
        try {
            
            ServletContext servletContext = arg0.getServletContext();
            String propPath = servletContext.getRealPath("profiles");
            Info.setConfig(new File(servletContext.getRealPath("conf")+ "\\config.properties"));
            File directory = new File(propPath);
            String numberOfProfileString;
            for (int i = 0; i < directory.list().length; i++) {
                Properties prop = new Properties();
                numberOfProfileString = Integer.toString(i);
                if (numberOfProfileString.length() == 1) {
                    numberOfProfileString = "00" + numberOfProfileString;
                } else if (numberOfProfileString.length() == 2) {
                    numberOfProfileString = "0" + numberOfProfileString;
                }
                try {
                    FileInputStream inputStream = new FileInputStream(propPath + "\\profile_" + numberOfProfileString + ".properties"); 
                    prop.load(inputStream);
                    inputStream.close();
                } catch (Exception ex) {
                    Logger.getLogger("detected only" + i-- + " profiles" + ex.getMessage());
                    break;
                }
                propertiesMap.put(prop.getProperty("ProfileName"), prop);
            }        
        BarcodeProcessor.setPropertiesMap(propertiesMap);
        } catch (Exception ex) {
            Logger.getLogger(AppServletContextListener.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
}
