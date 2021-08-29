//*****************************************************************************
//* QS-Barcode SDK barcode recognition - Java Example Code
//*
//* for SDK Version 4.6
//*
//* (c) 2007-2008
//* by QS QualitySoft GmbH, Zum Fuerstenmoor 11,21079 Hamburg,Germany
//* +49 (0)40 790 100 40 - www.qualitysoft.de - info@qualitysoft.de
//*****************************************************************************
package qualitysoft.qsBarcode;

public class BarcodeResult2 {
    public int iBC_Type;
    public int iBC_Type2;
    public int iBC_Status;
    public String szBC_Barcode; 
    public int iBC_StartX;
    public int iBC_StartY;
    public int iBC_SizeX;
    public int iBC_SizeY;
    public int iBC_Orientation;
    public TwoDimResult BC_TwoDimRes = new TwoDimResult();    
}
