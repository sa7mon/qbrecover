package com.danthesalmon.qbrecover.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.danthesalmon.qbrecover.model.Recovery;

import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;

public class Controller {
	
	@FXML
	private void initialize () {
		
	}
	
	@FXML
	private void click_btnSelectDrive () {
		// Function fired on btnSelectDrive click.
		
		DirectoryChooser dc = new DirectoryChooser(); 

        dc.setTitle("Choose drive");

        //Show open file dialog

        File file = dc.showDialog(null);

       if(file!=null){
            //System.out.println("File: " + file.getPath());
            
            do_recover(file.getPath().substring(0, 1));
       }
		
	}
	
	private void do_recover(String driveLetter) {
		
		Recovery c1 = new Recovery(driveLetter + ":\\");

        System.out.println("Dat file exists: " + c1.getDatFileExists());
        System.out.println("Version Name: " + c1.getVersionName());
        System.out.println("Version Year: " + c1.getVersionYear());
        System.out.println("License Number: " + c1.getLicenseNum());
        System.out.println("Product Number: " + c1.getProductNum());
	}

}
