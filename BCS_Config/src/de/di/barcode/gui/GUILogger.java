package de.di.barcode.gui;

/**
 *
 * @author E. Rahman
 */
import java.io.File;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

public class GUILogger {

    private static final String LOGGER_NAME = "GUILogger";
    private static Logger logger;

    public static void init(Config config) {

            String logfile = config.getProperty(Config.Property.DirectoriesLogging.toString()) + File.separator + LOGGER_NAME + ".log";

            // create pattern layout
            PatternLayout layout = new PatternLayout();
            String conversionPattern = "%d{dd.MM.yyyy HH\\:mm\\:ss} %-5p [%t]\\: %m%n";
            layout.setConversionPattern(conversionPattern);

            // create appender
            DailyRollingFileAppender appender = new DailyRollingFileAppender();
            appender.setDatePattern("'.'yyyy-MM-dd");
            appender.setFile(logfile);
            appender.setImmediateFlush(true);
            appender.setName("BCD_Config DailyRollingFile appender");
            appender.setLayout(layout);
            appender.activateOptions();

            // setup root logger
            Logger rootLogger = Logger.getRootLogger();
            rootLogger.addAppender(appender);
            String logLevel = config.getProperty(Config.Property.BasicLogLevel.toString());
            if (logLevel.compareToIgnoreCase("all") == 0) {
                rootLogger.setLevel(Level.ALL);
            } else if (logLevel.compareToIgnoreCase("info") == 0) {
                rootLogger.setLevel(Level.INFO);
            } else if (logLevel.compareToIgnoreCase("warn") == 0) {
                rootLogger.setLevel(Level.WARN);
            } else if (logLevel.compareToIgnoreCase("trace") == 0) {
                rootLogger.setLevel(Level.TRACE);
            } else if (logLevel.compareToIgnoreCase("debug") == 0) {
                rootLogger.setLevel(Level.DEBUG);
            } else if (logLevel.compareToIgnoreCase("fatal") == 0) {
                rootLogger.setLevel(Level.FATAL);
            } else if (logLevel.compareToIgnoreCase("error") == 0) {
                rootLogger.setLevel(Level.ERROR);
            }

            logger = Logger.getLogger("BCD_Config");
    }
    
    public static Logger getLogger(){
        return logger;
    }
}
