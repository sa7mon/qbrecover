package com.danthesalmon.qbrecover.model;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Recovery {

	//------------------------------ VARIABLES -------------------------------
    private Boolean boolDatExists = null;
    private Double dblVersionNum = null;
    private Integer intVersYear = 0;
    private String strDrive = "";
    private String datLocation = "";
    private String strErrCode = "";
    private String strFlavorName = "";
    private String strProdNum = null;
    private String strLicenseNum = "";
    private String strVersionName = "";

    //------------------------------ FUNCTIONS -------------------------------

    // Default constructor method
    public Recovery (String drive) {
        strDrive = drive;

        // See if registration file exists.
        File xpFile = new File(strDrive + "Documents and Settings\\All Users\\Application Data\\Common Files\\Intuit\\QuickBooks\\qbregistration.dat");
        File vista7File = new File (strDrive + "ProgramData\\Common Files\\Intuit\\QuickBooks\\qbregistration.dat");

        if (xpFile.exists()) {
        	boolDatExists = true;
            parseDat(xpFile);
        } else if (vista7File.exists()) {
        	boolDatExists = true;
            parseDat(vista7File);
        } else {
        	boolDatExists = false;
            strErrCode = "Couldn't find data file.";
            //DEBUG
            System.out.println(strErrCode);
        }
    }

    public void parseDat (File dat) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(dat);
            doc.getDocumentElement().normalize();

            dblVersionNum = Double.parseDouble(doc.getElementsByTagName("VERSION").item(0).getAttributes().item(0).getNodeValue());
            strFlavorName = doc.getElementsByTagName("FLAVOR").item(0).getAttributes().item(0).getNodeValue();

            Node node = doc.getElementsByTagName("QBREG").item(0);
            Element element = (Element) node;

            strLicenseNum = getValue("LicenseNumber",element);
            strProdNum = getValue("InstallID",element);

            // Convert version number to year
            versionNumToYear(dblVersionNum);

            // Convert flavor name to version name
            flavorNameToVersionName(strFlavorName);

        } catch (Exception exDefault) {
            strErrCode = "Couldn't parse license file.";
        }
    }

    public void versionNumToYear (Double versionNumber) {
        switch (versionNumber.toString()) {
            case "17.0":
                intVersYear = 2007;
                break;
            case "18.0":
                intVersYear = 2008;
                break;
            case "19.0":
                intVersYear = 2009;
                break;
            case "20.0":
                intVersYear = 2010;
                break;
            case "21.0":
                intVersYear = 2011;
                break;
            case "22.0":
                intVersYear = 2012;
                break;
            case "23.0":
                intVersYear = 2013;
                break;
            case "24.0":
                intVersYear = 2014;
                break;
            case "25.0":
                intVersYear = 2015;
                break;
            default:
                System.out.println("Error: Couldn't convert the product year.");
        }
    }

    public void flavorNameToVersionName (String flavorName) {
        /*
            Replace with atom if you have QuickBooks Simple Start.
            Replace with pro if you have QuickBooks Pro.
            Replace with superpro if you have QuickBooks Premier (not Accountant Edition).
            Replace with accountant if you have QuickBooks Premier Accountant Edition.
            Replace with bel if you have QuickBooks Enterprise Solutions (not Accountant Edition).
            Replace with belacct if you have QuickBooks Enterprise Solutions Accountant Edition.
         */
        switch (strFlavorName) {
            case "atom":
                strVersionName = "Simple Start";
                break;
            case "pro":
                strVersionName = "Pro";
                break;
            case "superpro":
                strVersionName = "Premier";
                break;
            case "accountant":
                strVersionName = "Premier Accountant Edition";
                break;
            case "bel":
                strVersionName = "Enterprise Solutions";
                break;
            case "belacct":
                strVersionName = "Enterprise Solutions Accountant Edition";
                break;
            default:
            	strErrCode += "Couldn't parse Version Name.";
                break;
        }
    }

    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodes.item(0);
        return node.getNodeValue();
    }


    //--------------------------- GETTERS & SETTERS ----------------------------

    public String getDatLocation () { return this.datLocation; }

    public String getDrive() {
        return this.strDrive;
    }

    public String getErrorCode() {
    	return this.strErrCode;
    }
    
    public String getVersionName () {
        return this.strVersionName;
    }

    public String getLicenseNum() {
        return this.strLicenseNum;
    }

    public Boolean getDatFileExists() {
        return this.boolDatExists;
    }

    public String getProductNum () {
        return this.strProdNum;
    }

    public Double getVersionNum () {
        return this.dblVersionNum;
    }

    public String getFlavorName () {
        return this.strFlavorName;
    }

    public Integer getVersionYear () {
        return this.intVersYear;
    }


}
