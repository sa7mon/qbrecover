package com.danthesalmon.qbrecover;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {

    //------------------------ VARIABLES -------------------------
    private String strDrive = "";
    private String strOS = "";
    private String strVersionNum = "";
    private String datLocation = "";


    //------------------------ FUNCTIONS -------------------------

    // Default constructor method
    public Controller (String drive) {

        strDrive = drive;
        strOS = System.getProperty("os.name");

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
                datLocation = strDrive + "ProgramData\\Common Files\\Intuit\\QuickBooks\\qbregistration.dat";
                break;
            default:
                System.out.println("Couldn't detect system OS");
        }

       // Path xpFolder = Paths.get(strDrive + ":\");

        // Determine which OS we are in based on the drive's folders (XP or 7/8/8.1)
        //if (Files.exists(pathDrive)) {

        //}

    }

    // GETTERS
    public String getOS() {
        return this.strOS;
    }


}
