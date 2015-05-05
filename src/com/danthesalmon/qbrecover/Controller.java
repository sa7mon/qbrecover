package com.danthesalmon.qbrecover;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {

    //------------------------ VARIABLES -------------------------
    private String strDrive = "";
    private String strOS = "";
    private String strVersionNum = "";



    //------------------------ FUNCTIONS -------------------------

    // Default constructor method
    public Controller (String drive) {
        strDrive = drive;

        strOS = System.getProperty("os.name");
        System.out.println("OS Found: " + strOS);
       // Path xpFolder = Paths.get(strDrive + ":\");

        // Determine which OS we are in based on the drive's folders (XP or 7/8/8.1)
        //if (Files.exists(pathDrive)) {

        //}

    }


}
