package de.di.barcodeserver.barcode;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author Rahman
 * @reviewed Cioaba Stefan + Mihai Paun
 */
public class Info {

    private static File config;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String serviceStartup = sdf.format(new Date());

    private static int counterOK = 0;
    private static int counterError = 0;

    private static int pollTime = 5000;

    private static Properties properties = new Properties();

    private static final Logger logger = Logger.getLogger(Info.class);

    public static int getCounterOK() {
        return counterOK;
    }

    public static void setCounterOK(int counterOK) {
        Info.counterOK = counterOK;
    }

    public static int getCounterError() {
        return counterError;
    }

    public static void setCounterError(int counterError) {
        Info.counterError = counterError;
    }

    public static String getServiceStartup() {
        return serviceStartup;
    }

    public static void setConfig(File conf) {
        config = conf;
        try {
            FileInputStream inputStream = new FileInputStream(config.getAbsolutePath());
            properties.load(inputStream);
            inputStream.close();
            pollTime = Integer.parseInt(properties.getProperty("Application.PollTime", String.valueOf(pollTime)));
        } catch (Exception ex) {
            logger.error(ex, ex);
        }

        config = conf;
    }

    public static File getConfig() {
        return config;
    }

    public static int getNumberOf() {
        return BarcodeProcessor.getPropertiesMap().size();
    }

    public static String[] getProfileInfo(int profileNumber) {
        Map<String, Properties> map = BarcodeProcessor.getPropertiesMap();
        String[] profileName = new String[2];
        int i = 0;
        for (Map.Entry<String, Properties> e : map.entrySet()) {
            Properties prop = new Properties();
            prop = e.getValue();
            if (i == profileNumber) {
                profileName[0] = prop.getProperty("ProfileName");
                profileName[1] = prop.getProperty("Action");
                break;
            }
            i++;
        }
        return profileName;
    }

    public static String getLoggingInfo() {
//        logger.debug("No properties!!??");
        for (String propertyName : properties.stringPropertyNames()) {
//            logger.debug("property: " + propertyName);
        }
        return properties.getProperty("Basic.LogLevel", "NON FOUND");
    }

    //incorect this returns only the actual time
    public static String getLastRun() {
        return sdf.format(new Date());
    }

    public static String getNextRun() {
        return sdf.format(new Date(new Date().getTime() + pollTime));
    }

    public static String getConfProperty(String prop) {
        if (properties != null) {
            return properties.getProperty(prop);
        }
        return null;
    }
}
