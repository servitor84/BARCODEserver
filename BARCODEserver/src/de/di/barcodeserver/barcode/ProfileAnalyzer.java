package de.di.barcodeserver.barcode;

import de.di.barcodeserver.profiles.ReadBarcode;
import java.util.Properties;
import org.apache.log4j.Logger;
import qualitysoft.qsBarcode.Barcode2;
import qualitysoft.qsBarcode.Defines;

/**
 *
 * @author Rahman
 */
public class ProfileAnalyzer {

    private static Barcode2 oBarcode2 = null;

    public static Barcode2 initBarcode(Properties profile, Logger logger) {
        oBarcode2 = new Barcode2();
        int iBC_Type = 0;
        // QR-Code
        if (profile.containsKey("BC_QRCODE") && profile.getProperty("BC_QRCODE").equals("1")) {
            iBC_Type += Defines.bcType.BC_QRCODE;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_QRCODE from Defines " + Defines.bcType.BC_QRCODE);
            }
        }
        // +++ END +++        
        if (profile.containsKey("BC_INTERLEAVED25") && profile.getProperty("BC_INTERLEAVED25").equals("1")) {
            iBC_Type += Defines.bcType.BC_INTERLEAVED25;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_INTERLEAVED25 from Defines " + Defines.bcType.BC_INTERLEAVED25);
            }
        }
        if (profile.containsKey("BC_INDUSTRIE25") && profile.getProperty("BC_INDUSTRIE25").equals("1")) {
            iBC_Type += Defines.bcType.BC_INDUSTRIE25;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_INDUSTRIE25 from Defines " + Defines.bcType.BC_INDUSTRIE25);
            }
        }
        if (profile.containsKey("BC_EAN8") && profile.getProperty("BC_EAN8").equals("1")) {
            iBC_Type += Defines.bcType.BC_EAN8;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_EAN8 from Defines " + Defines.bcType.BC_EAN8);
            }
        }
        if (profile.containsKey("BC_EAN13") && profile.getProperty("BC_EAN13").equals("1")) {
            iBC_Type += Defines.bcType.BC_EAN13;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_EAN13 from Defines " + Defines.bcType.BC_EAN13);
            }
        }
        if (profile.containsKey("BC_UPC_A") && profile.getProperty("BC_UPC_A").equals("1")) {
            iBC_Type += Defines.bcType.BC_UPC_A;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_UPC_A from Defines " + Defines.bcType.BC_UPC_A);
            }
        }
        if (profile.containsKey("BC_UPC_E") && profile.getProperty("BC_UPC_E").equals("1")) {
            iBC_Type += Defines.bcType.BC_UPC_E;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_UPC_E from Defines " + Defines.bcType.BC_UPC_E);
            }
        }
        if (profile.containsKey("BC_CODE39") && profile.getProperty("BC_CODE39").equals("1")) {
            iBC_Type += Defines.bcType.BC_CODE39;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_CODE39 from Defines " + Defines.bcType.BC_CODE39);
            }
        }
        if (profile.containsKey("BC_CODE93") && profile.getProperty("BC_CODE93").equals("1")) {
            iBC_Type += Defines.bcType.BC_CODE93;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_CODE93 from Defines " + Defines.bcType.BC_CODE93);
            }
        }
        if (profile.containsKey("BC_CODE93EXT") && profile.getProperty("BC_CODE93EXT").equals("1")) {
            iBC_Type += Defines.bcType.BC_CODE93EXT;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_CODE93EXT from Defines " + Defines.bcType.BC_CODE93EXT);
            }
        }
        if (profile.containsKey("BC_CODABAR") && profile.getProperty("BC_CODABAR").equals("1")) {
            iBC_Type += Defines.bcType.BC_CODABAR;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_CODABAR from Defines " + Defines.bcType.BC_CODABAR);
            }
        }
        if (profile.containsKey("BC_CODE128") && profile.getProperty("BC_CODE128").equals("1")) {
            iBC_Type += Defines.bcType.BC_CODE128;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_CODE128 from Defines " + Defines.bcType.BC_CODE128);
            }
        }
        if (profile.containsKey("BC_EAN128") && profile.getProperty("BC_EAN128").equals("1")) {
            iBC_Type += Defines.bcType.BC_EAN128;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_EAN128 from Defines " + Defines.bcType.BC_EAN128);
            }
        }
        if (profile.containsKey("BC_25_IATA") && profile.getProperty("BC_25_IATA").equals("1")) {
            iBC_Type += Defines.bcType.BC_25_IATA;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_25_IATA from Defines " + Defines.bcType.BC_25_IATA);
            }
        }
        if (profile.containsKey("BC_25_BCDMATRIX") && profile.getProperty("BC_25_BCDMATRIX").equals("1")) {
            iBC_Type += Defines.bcType.BC_25_BCDMATRIX;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_25_BCDMATRIX from Defines " + Defines.bcType.BC_25_BCDMATRIX);
            }
        }
        if (profile.containsKey("BC_25_BCDMATRIX") && profile.getProperty("BC_25_BCDMATRIX").equals("1")) {
            iBC_Type += Defines.bcType.BC_25_3MATRIX;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_25_3MATRIX from Defines " + Defines.bcType.BC_25_3MATRIX);
            }
        }
        if (profile.containsKey("BC_25_3DATALOGIC") && profile.getProperty("BC_25_3DATALOGIC").equals("1")) {
            iBC_Type += Defines.bcType.BC_25_3DATALOGIC;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_25_3DATALOGIC from Defines " + Defines.bcType.BC_25_3DATALOGIC);
            }
        }
        if (profile.containsKey("BC_25_INVERTIERT") && profile.getProperty("BC_25_INVERTIERT").equals("1")) {
            iBC_Type += Defines.bcType.BC_25_INVERTIERT;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_25_INVERTIERT from Defines " + Defines.bcType.BC_25_INVERTIERT);
            }
        }
        if (profile.containsKey("BC_CODE11") && profile.getProperty("BC_CODE11").equals("1")) {
            iBC_Type += Defines.bcType.BC_CODE11;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_CODE11 from Defines " + Defines.bcType.BC_CODE11);
            }
        }
        if (profile.containsKey("BC_CODE32") && profile.getProperty("BC_CODE32").equals("1")) {
            iBC_Type += Defines.bcType.BC_CODE32;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_CODE32 from Defines " + Defines.bcType.BC_CODE32);
            }
        }
        oBarcode2.iBC_Type = iBC_Type;
        if (profile.containsKey("BC_NONE") && profile.getProperty("BC_NONE").equals("1")) {
            oBarcode2.iBC_Type2 = Defines.bcType.BC_NONE;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("BC_NONE form Define " + Defines.bcType.BC_NONE);
            }
        }
        if (profile.containsKey("BC_LIGHTMARGIN") && profile.getProperty("BC_LIGHTMARGIN").isEmpty()) {
            oBarcode2.iBC_LightMargin = Defines.bcDefault.BC_LIGHTMARGIN;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_LIGHTMARGIN from Defines " + Defines.bcDefault.BC_LIGHTMARGIN);
            }
        } else {
            try {
                oBarcode2.iBC_LightMargin = Integer.parseInt(profile.getProperty("BC_LIGHTMARGIN"));
            } catch (Exception e) {
                logger.debug("Can not read from Profile BC_LIGHTMARGIN " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_LIGHTMARGIN from Profile " + profile.getProperty("BC_LIGHTMARGIN"));
            }
        }
        if (profile.containsKey("BC_MINHEIGHT") && profile.getProperty("BC_MINHEIGHT").isEmpty()) {
            oBarcode2.iBC_MinHeight = Defines.bcDefault.BC_MINHEIGHT;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_MINHEIGHT from Defines " + Defines.bcDefault.BC_MINHEIGHT);
            }
        } else {
            try {
                oBarcode2.iBC_MinHeight = Integer.parseInt(profile.getProperty("BC_MINHEIGHT"));
            } catch (Exception e) {
                logger.debug("Can not read from Profile BC_MINHEIGHT " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_MINHEIGHT from Profile " + profile.getProperty("BC_MINHEIGHT"));
            }
        }
        if (profile.containsKey("BC_MAXHEIGHT") && profile.getProperty("BC_MAXHEIGHT").isEmpty()) {
            oBarcode2.iBC_MaxHeight = Defines.bcDefault.BC_MAXHEIGHT;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_MAXHEIGHT from Defines " + Defines.bcDefault.BC_MAXHEIGHT);
            }
        } else {
            try {
                oBarcode2.iBC_MaxHeight = Integer.parseInt(profile.getProperty("BC_MAXHEIGHT"));
            } catch (Exception e) {
                logger.debug("Can not read from Profile BC_MAXHEIGHT " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_MAXHEIGHT from Profile " + profile.getProperty("BC_MAXHEIGHT"));
            }
        }
        if (profile.containsKey("BC_PERCENT") && profile.getProperty("BC_PERCENT").isEmpty()) {
            oBarcode2.iBC_Percent = Defines.bcDefault.BC_PERCENT;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_PERCENT from Defines " + Defines.bcDefault.BC_PERCENT);
            }
        } else {
            try {
                oBarcode2.iBC_Percent = Integer.parseInt(profile.getProperty("BC_PERCENT"));
            } catch (Exception e) {
                logger.debug("Can not read from Profile BC_PERCENT " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_PERCENT from Profile " + profile.getProperty("BC_PERCENT"));
            }
        }
        if (profile.containsKey("BC_SCANDISTANCE") && profile.getProperty("BC_SCANDISTANCE").isEmpty()) {
            oBarcode2.iBC_ScanDistance = Defines.bcDefault.BC_SCANDISTBAR;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_ScanDistance form Define " + Defines.bcDefault.BC_SCANDISTBAR);
            }
        } else {
            try {
                oBarcode2.iBC_ScanDistance = Integer.parseInt(profile.getProperty("BC_SCANDISTANCE"));
            } catch (Exception e) {
                logger.debug("Cannot read from Profile BCscanDist " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_ScanDistance form Profile " + profile.getProperty("BC_SCANDISTANCE"));
            }
        }
        if (profile.containsKey("BC_TOLERANCE") && profile.getProperty("BC_TOLERANCE").isEmpty()) {
            oBarcode2.iBC_Tolerance = Defines.bcDefault.BC_TOLERANCE;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_TOLERANCE form Define " + Defines.bcDefault.BC_TOLERANCE);
            }
        } else {
            try {
                oBarcode2.iBC_Tolerance = Integer.parseInt(profile.getProperty("BC_TOLERANCE"));
            } catch (Exception e) {
                logger.debug("Cannot read from Profile BC_TOLERANCE " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_TOLERANCE form Profile " + profile.getProperty("BC_TOLERANCE"));
            }
        }
        if (profile.containsKey("BC_SCANDISTBAR") && profile.getProperty("BC_SCANDISTBAR").isEmpty()) {
            oBarcode2.iBC_ScanDistBarcode = Defines.bcDefault.BC_SCANDISTBAR;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_SCANDISTBAR form Define " + Defines.bcDefault.BC_SCANDISTBAR);
            }
        } else {
            try {
                oBarcode2.iBC_ScanDistBarcode = Integer.parseInt(profile.getProperty("BC_SCANDISTBAR"));
            } catch (Exception e) {
                logger.debug("Cannot read from Profile BC_SCANDISTBAR " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_SCANDISTBAR form Profile " + profile.getProperty("BC_SCANDISTBAR"));
            }
        }
        if (profile.containsKey("BC_MAX_NO_VAL") && profile.getProperty("BC_MAX_NO_VAL").isEmpty()) {
            oBarcode2.iBC_MaxNoVal = Defines.bcDefault.BC_MAX_NO_VAL;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_MAX_NO_VAL form Define " + Defines.bcDefault.BC_MAX_NO_VAL);
            }
        } else {
            try {
                oBarcode2.iBC_MaxNoVal = Integer.parseInt(profile.getProperty("BC_MAX_NO_VAL"));
            } catch (Exception e) {
                logger.debug("Cannot read from Profile BC_MAX_NO_VAL " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_MAX_NO_VAL form Profile " + profile.getProperty("BC_MAX_NO_VAL"));
            }
        }
        if (profile.containsKey("BC_MULTI") && !profile.getProperty("BC_MULTI").isEmpty()) {
            try {
                oBarcode2.iBC_ReadMultiple = Integer.parseInt(profile.getProperty("BC_MULTI"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read BC_MULTI form Profile " + profile.getProperty("BC_MULTI"));
                }
            } catch (Exception e) {
                logger.debug("Cannot read from Profile BC_MULTI " + e.getMessage());
            }
        } else {
            oBarcode2.iBC_ReadMultiple = Defines.bcMultiRead.BC_MULTI;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_MULTI form Define " + Defines.bcMultiRead.BC_MULTI);
            }
        }
        if (profile.containsKey("iBC_Checksum") && !profile.getProperty("iBC_Checksum").isEmpty()) {
            try {
                oBarcode2.iBC_Checksum = Integer.parseInt(profile.getProperty("iBC_Checksum"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read iBC_Checksum from Profile " + profile.getProperty("iBC_Checksum"));
                }
            } catch (Exception e) {
                logger.debug("Cannot read from Profile iBC-Checksum " + e.getMessage());
            }
        } else {
            oBarcode2.iBC_Checksum = 0;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("iBC_Checksum is not containt in Profile and has the value 0");
            }
        }
        if (profile.containsKey("iBC_Length") && profile.getProperty("iBC_Length").isEmpty()) {
            oBarcode2.iBC_Length = 0;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("iBC_Length not found in profile, using default value 0");
            }
        } else {
            try {
                oBarcode2.iBC_Length = Integer.parseInt(profile.getProperty("iBC_Length"));
            } catch (Exception e) {
                logger.debug("Cannot read from Profile iBC_Length " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read iBC_Length from Profile " + profile.getProperty("iBC_Length"));
            }
        }
        if (profile.containsKey("iLaengeVon") && profile.getProperty("iLaengeVon").isEmpty()) {
            oBarcode2.iLaengeVon = 4;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("iLaengeVon is not containt in Profile and has the value 4");
            }
        } else {
            try {
                oBarcode2.iLaengeVon = Integer.parseInt(profile.getProperty("iLaengeVon"));
            } catch (Exception e) {
                logger.debug("Cannot read from Pprofile iLaengeVon " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read iLaengeVon from Profile " + profile.getProperty("iLaengeVon"));
            }
        }
        if (profile.containsKey("iLaengeBis") && profile.getProperty("iLaengeBis").isEmpty()) {
            oBarcode2.iLaengeBis = 64;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("iLaengeBis is not containt in Profile and has the value 64");
            }
        } else {
            try {
                oBarcode2.iLaengeBis = Integer.parseInt(profile.getProperty("iLaengeBis"));
            } catch (Exception e) {
                logger.debug("Cannot read from Profile iLaengeBis " + profile.getProperty("iLaengeBis"));
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read iLaengeBis from Profile " + profile.getProperty("iLaengeBis"));
            }
        }
        if (profile.containsKey("iBC_SizeX") && !profile.getProperty("iBC_SizeX").isEmpty()) {
            try {
                oBarcode2.iBC_SizeX = Integer.parseInt(profile.getProperty("iBC_SizeX"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read iBC_SizeX from Profile " + profile.getProperty("iBC_SizeX"));
                }
            } catch (Exception e) {
                logger.debug("Cannot read from Profile iBC_SizeX " + e.getMessage());
            }
        } else {
            oBarcode2.iBC_SizeX = 0;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("iBC_SizeX is not containt in Profile and has the value 0");
            }
        }
        if (profile.containsKey("iBC_SizeY") && !profile.getProperty("iBC_SizeY").isEmpty()) {
            try {
                oBarcode2.iBC_SizeY = Integer.parseInt(profile.getProperty("iBC_SizeY"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read iBC_SizeY from Profile " + profile.getProperty("iBC_SizeY"));
                }
            } catch (Exception e) {
                logger.debug("Cannot read from Profile iBC_SizeY " + e.getMessage());
            }
        } else {
            oBarcode2.iBC_SizeY = 0;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("iBC_SizeY is not containt in Profile and has the value 0");
            }
        }
        if (profile.containsKey("iBC_StartX") && !profile.getProperty("iBC_StartX").isEmpty()) {
            try {
                oBarcode2.iBC_StartX = Integer.parseInt(profile.getProperty("iBC_StartX"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read iBC_StartX from Profile " + profile.getProperty("iBC_StartX"));
                }
            } catch (Exception e) {
                logger.debug("Cannot read from Profile iBC_StartX " + e.getMessage());
            }
        } else {
            oBarcode2.iBC_StartX = 0;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("iBC_StartX is not containt in Profile and has the value 0");
            }
        }
        if (profile.containsKey("iBC_StartY") && !profile.getProperty("iBC_StartY").isEmpty()) {
            try {
                oBarcode2.iBC_StartY = Integer.parseInt(profile.getProperty("iBC_StartY"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read iBC_StartY from Profile " + profile.getProperty("iBC_StartY"));
                }
            } catch (Exception e) {
                logger.debug("Cannot read from Profile iBC_StartY " + e.getMessage());
            }
        } else {
            oBarcode2.iBC_StartY = 0;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("iBC_StartY is not containt in Profile and has the value 0");
            }
        }
        int bc_Orientation = 0;
        if (profile.containsKey("BC_0") && profile.getProperty("BC_0").equals("1")) {
            bc_Orientation = Defines.bcOrientation.BC_0;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_0 form Define " + Defines.bcOrientation.BC_0);
            }
        } else {
            try {
                bc_Orientation = Integer.parseInt(profile.getProperty("BC_0"));
            } catch (Exception e) {
                logger.debug("Cannot read from Profile BC_0 " + e.getMessage());
            }
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_0 from Profile " + profile.getProperty("BC_0"));
            }
        }
        if (profile.containsKey("BC_90") && profile.getProperty("BC_90").equals("1")) {
            bc_Orientation += Defines.bcOrientation.BC_90;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_90 form Define " + Defines.bcOrientation.BC_90);
            }
        } else {
            try {
                bc_Orientation += Integer.parseInt(profile.getProperty("BC_90"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read BC_90 from Profile " + profile.getProperty("BC_90"));
                }
            } catch (Exception e) {
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Cannot read from Profile BC_90 " + e.getMessage());
                }
            }
        }
        if (profile.containsKey("BC_180") && profile.getProperty("BC_180").equals("1")) {
            bc_Orientation += Defines.bcOrientation.BC_180;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_180 form Define " + Defines.bcOrientation.BC_180);
            }
        } else {
            try {
                bc_Orientation += Integer.parseInt(profile.getProperty("BC_180"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read BC_180 from Profile " + profile.getProperty("BC_180"));
                }
            } catch (Exception e) {
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Cannot read from Profile BC_180 " + e.getMessage());
                }
            }
        }
        if (profile.containsKey("BC_270") && profile.getProperty("BC_270").equals("1")) {
            bc_Orientation += Defines.bcOrientation.BC_270;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_270 form Define " + Defines.bcOrientation.BC_270);
            }
        } else {
            try {
                bc_Orientation += Integer.parseInt(profile.getProperty("BC_270"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read BC_270 from Profile " + profile.getProperty("BC_270"));
                }
            } catch (Exception e) {
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Cannot read from Profile BC_270 " + e.getMessage());
                }
            }
        }

        if (profile.containsKey("BC_SKEW_NONE") && profile.getProperty("BC_SKEW_NONE").equals("1")) {
            bc_Orientation += Defines.bcSkew.BC_SKEW_NONE;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_SKEW_NONE form Define " + Defines.bcSkew.BC_SKEW_NONE);
            }
        } else {
            try {
                bc_Orientation += Integer.parseInt(profile.getProperty("BC_SKEW_NONE"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read BC_SKEW_NONE from Profile " + profile.getProperty("BC_SKEW_NONE"));
                }
            } catch (Exception e) {
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Cannot read from Profile BC_SKEW_NONE " + e.getMessage());
                }
            }
        }

        if (profile.containsKey("BC_SKEW_LIGHT") && profile.getProperty("BC_SKEW_LIGHT").equals("1")) {
            bc_Orientation += Defines.bcSkew.BC_SKEW_LIGHT;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_SKEW_LIGHT form Define " + Defines.bcSkew.BC_SKEW_LIGHT);
            }
        } else {
            try {
                bc_Orientation += Integer.parseInt(profile.getProperty("BC_SKEW_LIGHT"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read BC_SKEW_LIGHT from Profile " + profile.getProperty("BC_SKEW_LIGHT"));
                }
            } catch (Exception e) {
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Cannot read from Profile BC_SKEW_LIGHT " + e.getMessage());
                }
            }
        }

        if (profile.containsKey("BC_SKEW_MEDIUM") && profile.getProperty("BC_SKEW_MEDIUM").equals("1")) {
            bc_Orientation += Defines.bcSkew.BC_SKEW_MEDIUM;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_SKEW_MEDIUM form Define " + Defines.bcSkew.BC_SKEW_MEDIUM);
            }
        } else {
            try {
                bc_Orientation += Integer.parseInt(profile.getProperty("BC_SKEW_MEDIUM"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read BC_SKEW_MEDIUM from Profile " + profile.getProperty("BC_SKEW_MEDIUM"));
                }
            } catch (Exception e) {
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Cannot read from Profile BC_SKEW_MEDIUM " + e.getMessage());
                }
            }
        }

        if (profile.containsKey("BC_SKEW_HEAVY") && profile.getProperty("BC_SKEW_HEAVY").equals("1")) {
            bc_Orientation += Defines.bcSkew.BC_SKEW_HEAVY;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_SKEW_HEAVY form Define " + Defines.bcSkew.BC_SKEW_HEAVY);
            }
        } else {
            try {
                bc_Orientation += Integer.parseInt(profile.getProperty("BC_SKEW_HEAVY"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read BC_SKEW_HEAVY from Profile " + profile.getProperty("BC_SKEW_HEAVY"));
                }
            } catch (Exception e) {
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Cannot read from Profile BC_SKEW_HEAVY " + e.getMessage());
                }
            }
        }


        if (profile.containsKey(
                "BC_SKEW_DENSE_SEARCH") && profile.getProperty("BC_SKEW_DENSE_SEARCH").equals("1")) {
            bc_Orientation += Defines.bcSkew.BC_SKEW_DENSE_SEARCH;
            if (ReadBarcode.firstTimeRuning) {
                logger.debug("Read BC_SKEW_DENSE_SEARCH form Define " + Defines.bcSkew.BC_SKEW_DENSE_SEARCH);
            }
        } else {
            try {
                bc_Orientation += Integer.parseInt(profile.getProperty("BC_SKEW_DENSE_SEARCH"));
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Read BC_SKEW_DENSE_SEARCH from Profile " + profile.getProperty("BC_SKEW_DENSE_SEARCH"));
                }
            } catch (Exception e) {
                if (ReadBarcode.firstTimeRuning) {
                    logger.debug("Cannot read from Profile BC_SKEW_DENSE_SEARCH " + e.getMessage());
                }
            }

        }
        oBarcode2.iBC_Orientation = bc_Orientation;
        return oBarcode2;

    }

}
