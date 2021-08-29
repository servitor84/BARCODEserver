package de.di.barcode.gui;

//import de.di.barcodeserver.app.StartUpException;
import de.di.dokinform.elo.ELOClientNG;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.log4j.BasicConfigurator;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import java.text.SimpleDateFormat;
import java.util.Date;

// NEU //
//import de.di.barcodeserver.elo.ELOClient;
import java.rmi.RemoteException;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
// +++ //


/**
 * The main class of the application.
 */
public class ConfigApp extends SingleFrameApplication {

    private final static String bundleName = "de/di/barcode/gui/resources/ConfigApp";
    private final static String bundleName1 = "de/di/barcode/resources/product";

    private String appVersion = java.util.ResourceBundle.getBundle(bundleName1).getString("app.version");
    private String appBuild = java.util.ResourceBundle.getBundle(bundleName1).getString("app.build");
    private String appTitle = java.util.ResourceBundle.getBundle(bundleName).getString("Application.title");

    //wrapper peste Properties + Handler
    Config config = null;
    
    public static ELOClientNG client;    
    // NEU //
    //public static ELOClient client;
    private InputStream configStream = null;
    private static Properties settings = null;
    private static Logger logger = null;
    // +++ //

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        BasicConfigurator.configure();

        config = loadConfig();
        config.put(Config.Property.ApplicationTitle.toString(), appTitle);
        config.put(Config.Property.ApplicationVersion.toString(), appVersion);
        config.put(Config.Property.ApplicationBuild.toString(), appBuild);

        GUILogger.init(config);
        
        try {
            // SL - Auskommentiert
            client = new ELOClientNG(config); // client = new ELOClientNG(config, GUILogger.getLogger());
            
            
            // NEU //
//            settings = new Properties();
//            File configFile = null;
//            if (configStream == null) {
//                try {
//                    configFile = new File("../conf/config.properties");
//                } catch (Exception ex) {
//                    logger.log(Level.FATAL, "Unable to locate config file.", ex);                    
//                }
//                if (configFile.canRead() == false) {
//                    logger.log(Level.FATAL, "Unable to read config file '" + configFile.getAbsolutePath() + "'.");                    
//                }
//                try {
//                    configStream = new FileInputStream(configFile);
//                } catch (FileNotFoundException fex) {
//                    logger.log(Level.FATAL, "Config file '" + configFile.getAbsolutePath() + "' not found.");                    
//                }
//            }            
//            try {
//                settings.load(configStream);
//                configStream.close();
//            } catch (java.io.IOException ioex) {
//                logger.log(Level.FATAL,
//                    "Unable to read config file 'config.properties'.");
//                logger.log(Level.FATAL, "File is corrupt!");                
//            }
//            String ix = settings.getProperty("IndexServer.URL");
//            String user = settings.getProperty("IndexServer.User");
//            String password = settings.getProperty("IndexServer.Password");
//            client = new ELOClient();
//            client.setConnectionUrl(ix);
//            client.setUserName(user);
//            client.setPassword(password);
//            client.init();
//            while (!client.isConnected()) {
//                try {
//                    client.login();
//                } catch (RemoteException ex) { //login failed
//                    for (StackTraceElement elem : ex.getStackTrace()) {
//                        logger.trace(elem.getFileName() + ":" + elem.getLineNumber() + " "
//                                + elem.getClassName() + "." + elem.getMethodName());
//                    }
//                    logger.debug("An exception occured while connecting to the index server.", ex);
//                    logger.error("Cannot connect to elo indexserver at the following addresses \'" + settings.getProperty("IndexServer.URL") + "\' ");
//                    try {
//                        Thread.sleep(Integer.valueOf(settings.getProperty("Application.PollTime"))); //wait 5s for next try
//                    } catch (InterruptedException ex1) {}
//                } catch(Exception ex){
//                    logger.debug("An exception occured while connecting to the index server.", ex);
//                    logger.error("Cannot connect to elo indexserver at the following addresses \'" + settings.getProperty("IndexServer.URL") + "\' ");
//                    try {
//                        Thread.sleep(Integer.valueOf(settings.getProperty("Application.PollTime"))); //wait 5s for next try
//                    } catch (InterruptedException ex1) {}
//                }                    
//            }   
//            // +++ //
        } catch (Exception ex) {
            GUILogger.getLogger().error("Could not create ELO client", ex);
        }
        show(new ConfigView(this));

    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {

        getMainFrame().setIconImage(getContext().getResourceMap(getClass()).getImageIcon("fav.icon").getImage());
        getMainFrame().setTitle(appTitle + ' ' + appVersion + " Build(" + appBuild + ")");
    }

    /**
     * A convenient static getter for the application instance.
     *
     * @return the instance of ERPGUIApp
     */
    public static ConfigApp getApplication() {
        return Application.getInstance(ConfigApp.class);
    }

    public Config getConfig() {
        return config;
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        Date timeStamp = new Date();
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String message = "The ConfigurationGUI is started at " + date.format(timeStamp) + "\n";
        message += "From user : " + System.getProperty("user.name") + "\n";
        try {
            java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
            message += "Host machine : " + localMachine.getHostName();
        } catch (Exception ex) {

        }
        launch(ConfigApp.class, args);
    }

    void saveConfig() throws Exception {
        File configFile = null;

        OutputStream configStream = null;

        try {
            configFile = new File("../conf/config.properties");

            if (configFile.canWrite() == false) {
                throw new Exception(java.util.ResourceBundle.getBundle(
                        bundleName).
                        getString("noWriteAccess.text"));
            }
        } catch (Exception ex) {
            throw new Exception(java.util.ResourceBundle.getBundle(
                    bundleName).
                    getString("unableToWriteFile.text"));
        }

        try {
            configStream = new FileOutputStream(configFile);
        } catch (FileNotFoundException fex) {
            throw new Exception(java.util.ResourceBundle.getBundle(
                    bundleName).
                    getString("canNotWriteFile.text"));
        }

        try {            
            this.config.store(configStream, "Configuration file");
        } catch (java.io.IOException ioex) {
            throw new Exception(java.util.ResourceBundle.getBundle(
                    bundleName).
                    getString("errorOnWritingFile.text"));
        }

    }

    private Config loadConfig() {
        Config props = new Config();

        File configFile = null;

        InputStream configStream = null;
        try {
            configFile = new File("../conf/config.properties");

            if (configFile.canRead() == false) {
                GUILogger.getLogger().info(java.util.ResourceBundle.getBundle(
                        bundleName).getString(
                                "fileUnreadable.text"));
                //TODO: Handle error

                return props;
            }
        } catch (Exception ex) {
            //TODO: Handle exception

            return props;
        }

        try {
            configStream = new FileInputStream(configFile);
        } catch (FileNotFoundException fex) {
            //TODO: Handle exception
            return props;
        }

        try {
            props.load(configStream);
            // PWD: Decode ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            String password = props.getProperty("IndexServer.Password");
//            String pattern = "^((\\d){1,})([-]{1}(\\d){1,}){1,}";
//            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
//            java.util.regex.Matcher matcher = p.matcher(password);
//            if(matcher.matches()) {
//                de.elo.utils.sec.DesEncryption des = new de.elo.utils.sec.DesEncryption();
//                password = des.decrypt(password);
//            }
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            props.setProperty("IndexServer.Password", password); 
        } catch (java.io.IOException ioex) {
            //TODO: Handle exception
        } catch (Exception ex) {            
        }

        return props;
    }
}
