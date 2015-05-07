package com.danthesalmon.qbrecover;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class Controller {

    //------------------------------ VARIABLES -------------------------------
    private String strDrive = "";
    private String strOS = "";
    private String strVersionNum = "";
    private String datLocation = "";
    private String strErrCode = "";
    private Boolean boolDatExists = null;

    // Product Number, License Number, Version Year, Version Name

    private Integer intVersionNum = null;
    private String strFlavorName = "";
    private Integer intProdNum = null;


    //------------------------------ FUNCTIONS -------------------------------


    // Default constructor method
    public Controller (String drive) {

        strDrive = drive;

        // Detect OS
        strOS = System.getProperty("os.name");

        // Determine qbregistration.dat location.
        switch (strOS) {
            case "Windows XP":
                datLocation = strDrive + "Documents and Settings\\All Users\\Application Data\\Common Files\\Intuit\\QuickBooks\\qbregistration.dat";
                break;
            case "Windows Vista":
                datLocation = strDrive + "ProgramData\\Common Files\\Intuit\\QuickBooks\\qbregistration.dat";
                break;
            case "Windows 7":
                datLocation = strDrive + "ProgramData\\Common Files\\Intuit\\QuickBooks\\qbregistration.dat";
                break;
            case "windows 8":
                datLocation = strDrive + "ProgramData\\Common Files\\Intuit\\QuickBooks\\qbregistration.dat";
                break;
            case "Windows 8.1":
                datLocation = strDrive + ":\\ProgramData\\Common Files\\Intuit\\QuickBooks\\qbregistration.dat";
                break;
            default:
                // TODO Show user the error message somehow
                System.out.println("Couldn't detect system OS");
        }

        // See if registration file exists.
        File datFile = new File(datLocation);
        boolDatExists = datFile.exists();

        // If the dat file exists, parse it and get what we need.
        //Dat dat1 = new Dat(datFile);
        parseDat (datFile);
    }

    public void parseDat (File dat) {
        System.out.println("Parsing...");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(dat);
            doc.getDocumentElement().normalize();

            System.out.println(doc.getElementsByTagName("VERSION").item(0).getAttributes().item(0).getNodeValue());

            NodeList nodes = doc.getElementsByTagName("QBREG");
            //nodes.item(0).getNodeName().toString() returns ^^

            System.out.println("Version Number: " + nodes.item(0).getNodeName().toString());

            // Iterate through nodes in nodelist
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                Element element = (Element) node;

                System.out.println("Lic: " + getValue("LicenseNumber",element));
            }

        } catch (Exception exDefault) {
            System.out.println(exDefault.toString());
        }

    }

    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }

    // GETTERS
    public String getOS() {
        return this.strOS;
    }

    public String getDatLocation () {
        return this.datLocation;
    }

    public String getDrive() {
        return this.strDrive;
    }

    public Boolean getDatFileExists() {
        return this.boolDatExists;
    }
}
