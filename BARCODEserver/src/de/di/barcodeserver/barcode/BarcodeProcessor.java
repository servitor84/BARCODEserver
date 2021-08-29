package de.di.barcodeserver.barcode;

import com.lowagie.text.pdf.PdfReader;
import static de.di.barcodeserver.barcode.BarcodeProcessor.currentProfileName;
import java.io.IOException;
import java.util.*;
import java.util.Properties;
import org.apache.log4j.Logger;
import qualitysoft.qsBarcode.Barcode2;
import qualitysoft.qsBarcode.BarcodeResult2;
import qualitysoft.qsBarcode.Defines;
import qualitysoft.qsBarcode.Dll;

/**
 *
 * @author A. Sopicki
 */
public class BarcodeProcessor {

    private Logger logger;
    private Barcode2 oBarcode2 = null;
    public static String realPath = "";
    private BarcodeResult2 oResult2 = null;
    public static String currentProfileName = "";
    private Properties profile = new Properties();
    private static BarcodeProcessor instance = null;
    private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();

    public static void setPropertiesMap(Map<String, Properties> map) {
        propertiesMap = map;
    }

    public static Map<String, Properties> getPropertiesMap() {
        return propertiesMap;
    }

    public BarcodeProcessor() {
        logger = Logger.getLogger(getClass());
        currentProfileName = currentProfileName.substring((currentProfileName.indexOf("{") + 1), currentProfileName.indexOf("}"));
        if (propertiesMap.containsKey(currentProfileName)) {
            profile = propertiesMap.get(currentProfileName);
            logger.info("Using profile name '" + profile.getProperty("ProfileName") + "'");
        } else {
            logger.info("A profile with ProfileName '" + currentProfileName + "' doesn't exist in folder 'profiles'");
        }
        init();
    }

    public static synchronized BarcodeProcessor getInstance() {
        if (instance == null) {
            instance = new BarcodeProcessor();
        }
        return instance;
    }

    private void init() {
        String sVersion = new String();
        int iLicense = 0;
        logger.info("DI-Barcode start loading DLL... (for errors check tomcat stderr-logfile");
        sVersion = Dll.qsVersion();
        logger.info("DI-Barcode Recognition Engine Version is: " + sVersion);
        iLicense = Dll.qsLicense();
        logger.info("DI-Barcode Recognition Engine License Test:");
        if ((iLicense & Defines.bcLicense.BC_LIC_DEMO) > 0) {
            logger.warn(" -> DI-Barcode DEMO-Mode active!");
        }
        if ((iLicense & Defines.bcLicense.BC_LIC_LINEAR) > 0) {
            logger.info(" -> DI-Barcode LINEAR BARCODES enabled!");
        }
        if ((iLicense & Defines.bcLicense.BC_LIC_DATAM) > 0) {
            logger.info(" -> DI-Barcode DATA MATRIX enabled!");
        }
        if ((iLicense & Defines.bcLicense.BC_LIC_PDF417) > 0) {
            logger.info(" -> DI-Barcode PDF417 enabled!");
        }
        if ((iLicense & Defines.bcLicense.BC_LIC_QRCODE) > 0) {
            logger.info(" -> DI-Barcode QRCODE enabled!");
        }
    }

    public java.util.List<String> readBarcodes(java.io.File f) {
        return readBarcodes(f, 1);
    }

    public java.util.List<String> readBarcodes(java.io.File f, int page) {
        int iRC = 0;
        //make sure barcode data structure is initialised
        if (oBarcode2 == null) {
            initBarcode2();
        }
        java.util.LinkedList<String> results = new java.util.LinkedList<String>();
        logger.debug("Reading file: " + f);
        logger.info("Page: " + page);
        iRC = Dll.qsReadBarcode2(f.getAbsolutePath() + "<" + page, oBarcode2, 50);
        logger.debug("#### DI-Barcode RC: " + iRC);
        if (iRC == Defines.bcError.BC_NO_SUCH_PAGE && page == 1) {
            logger.info("Temp file not found skip workflow task");
            results = null;
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            /* SL - 23.01.2020
            Prüfen, ob PDF Datei beschädigt ist. Und eine entprechende Meldung ausgeben.
            */
            try {
                PdfReader reader = new PdfReader(f.getAbsolutePath());
            } catch (IOException ex) {
                logger.error("Can not read Temp-File: " + f.getAbsolutePath() + " because " + ex.getMessage());
            }
            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++SL
        } else {
            if (iRC == Defines.bcError.BC_NO_SUCH_PAGE) {
                logger.debug("No such page: " + page + " in file " + f);
                Dll.qsFreeBarcode2();
                oBarcode2 = null;
                oResult2 = null;
                return null;
            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++SL - 03.09.2020
            } 
            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            while (iRC == Defines.bcError.BC_OK) {
                iRC = Dll.qsGetNextResult2(oResult2);
                if (iRC == Defines.bcError.BC_OK) {
                    if (oResult2.BC_TwoDimRes.iBC_TwoDimLen != 0) {
                        // 2-dimensional result                                               
                        StringBuffer buffer = new StringBuffer();
                        for (int i = 0; i < oResult2.BC_TwoDimRes.iBC_TwoDimLen; i++) {
                            buffer.append((char) oResult2.BC_TwoDimRes.aBC_TwoDimResult[i]);
                        }
                        logger.debug("Result (2D): " + buffer.toString());                                                
                        //results.add(buffer.toString() + "¶" + page);
                         if(profile.getProperty("BC_Character") != null && !profile.getProperty("BC_Character").equals("") 
                                && profile.getProperty("BC_included").equalsIgnoreCase("TRUE")) { // Wort enthalten => Skip: Wort darf nicht vorkommen
                            String pattern = profile.getProperty("BC_Character");
                            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
                            java.util.regex.Matcher matcher = p.matcher(buffer.toString());
                            if (matcher.matches()) {
                                
                            }                            
                            // END
                            else {
                                results.add(buffer.toString() + "¶" + page);
                            }
                        } else if(profile.getProperty("BC_Character") != null && !profile.getProperty("BC_Character").equals("")
                                && profile.getProperty("BC_included").equalsIgnoreCase("FALSE")) { // Wort nicht enthalten => Skip: Wort muss vorkommen
                            String pattern = profile.getProperty("BC_Character");
                            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
                            java.util.regex.Matcher matcher = p.matcher(buffer.toString());
                            if (!matcher.matches()) {
                                
                            }                            
                            // END
                            else {
                                results.add(buffer.toString() + "¶" + page);
                            }
                        } else {
                            results.add(buffer.toString() + "¶" + page);
                        }
//                        if (page == 1){
//                            break;
//                        }
                    // ++++++++++++++ +++++++++++++++++ ++++++++++++++++
                    } else {
                        // 1-dimensional result
                        logger.info("Result: " + oResult2.szBC_Barcode);
                        // UG 2011-11-17: added pagenumber to ìndex string
                        // SL 2018-05-11: if barcode string beginnt with value of (BC_Character), it will be skiped                        
                        if(profile.getProperty("BC_Character") != null && !profile.getProperty("BC_Character").equals("") 
                                && profile.getProperty("BC_included").equalsIgnoreCase("TRUE")) { // Wort enthalten => Skip: Wort darf nicht vorkommen
                            String pattern = profile.getProperty("BC_Character");
                            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
                            java.util.regex.Matcher matcher = p.matcher(oResult2.szBC_Barcode);
                            if (!matcher.matches()) {
                                results.add(oResult2.szBC_Barcode + "¶" + page);
                            }
                        } else if(profile.getProperty("BC_Character") != null && !profile.getProperty("BC_Character").equals("")
                                && profile.getProperty("BC_included").equalsIgnoreCase("FALSE")) { // Wort nicht enthalten => Skip: Wort muss vorkommen
                            String pattern = profile.getProperty("BC_Character");
                            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
                            java.util.regex.Matcher matcher = p.matcher(oResult2.szBC_Barcode);
                            if (matcher.matches()) {
                                results.add(oResult2.szBC_Barcode + "¶" + page);
                            }
                        } else {
                            results.add(oResult2.szBC_Barcode + "¶" + page);
                        }
                        // END
//                        if (page == 1){
//                            break;
//                        }
                    }
                } else if (iRC == Defines.bcError.BC_EXISTS) {
                    logger.debug("Result: BARCODE EXISTS");
                }
            }
        }
        Dll.qsFreeBarcode2();
        oBarcode2 = null;
        oResult2 = null;
        return results;
    }

    public void initBarcode2() {
        oBarcode2 = ProfileAnalyzer.initBarcode(profile, logger);
        oResult2 = new BarcodeResult2();
    }

    public void setScanArea(java.awt.Rectangle area) {
        oBarcode2.iBC_StartX = (int) area.getX();
        oBarcode2.iBC_StartY = (int) area.getY();
        oBarcode2.iBC_SizeX = (int) (area.getX() + area.getWidth());
        oBarcode2.iBC_SizeY = (int) (area.getY() + area.getHeight());
    }

    public void setBarcodeTypes(int types) {
        oBarcode2.iBC_Type = types;
    }       

    public Properties getProfile() {
        return profile;
    }        
}