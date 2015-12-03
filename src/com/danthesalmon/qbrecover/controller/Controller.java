package com.danthesalmon.qbrecover.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.danthesalmon.qbrecover.model.Recovery;
import com.danthesalmon.qbrecover.view.*;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller {
	
	//--------------------- GUI ELEMENTS --------------------
	@FXML
	private Button btnSelectDrive;
	
	@FXML
    private Label lblVersionName;
	
	@FXML
    private Label lblVersionYear;
	
	@FXML
	private Label lblStatus;
	
	@FXML
    private TextField txtLicenseNum;
	
	@FXML
    private TextField txtProdNum;
	
	@FXML
	private MenuItem btnClose;
	
	@FXML
	private MenuItem btnSave;
	
	final Popup popupSuccess = new Popup();
	
	@FXML
	private void initialize () {
		
	}
	
	private Recovery c1;
	
	@FXML
	private void click_btnSave () {
		// Save the license info to file.
		
		FileChooser saveChooser = new FileChooser();
		FileChooser.ExtensionFilter saveFilter = new FileChooser.ExtensionFilter("Text File", "*.txt");
		
		saveChooser.setTitle("qbrecover - Save License Info");
		saveChooser.getExtensionFilters().add(saveFilter);
		
		File file = saveChooser.showSaveDialog(View.primaryStage);
		
		if (file != null) {
            try {
                // Try to save the text file.
            	PrintWriter out = new PrintWriter(file.getAbsolutePath());
            	out.println("Recovered QuickBooks License - qbrecover");
            	out.println();
            	out.println("Version Name:   " + c1.getVersionName());
            	out.println("Version Year:   " + c1.getVersionYear().toString());
            	out.println("License Number: " + c1.getLicenseNum());
            	out.println("Product Number: " + c1.getProductNum());            	
            	out.close();
            	
            	// Change status label to success
            	lblStatus.setText("Information saved to file successfully!");
            	
            	// Show success popup
//            	final Stage dialog = new Stage();
//                dialog.initModality(Modality.APPLICATION_MODAL);
//                dialog.initOwner(View.primaryStage);
//                VBox dialogVbox = new VBox(20);
//                dialogVbox.getChildren().add(new Text("This is a Dialog"));
//                Scene dialogScene = new Scene(dialogVbox, 300, 200);
//                dialog.setScene(dialogScene);
//                dialog.show();
            } 
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
	
	@FXML
	private void click_btnClose () {
		System.exit(0);
	}
	
	@FXML
	private void click_btnSelectDrive () {
		// Function fired on btnSelectDrive click.
		
		DirectoryChooser dc = new DirectoryChooser(); 
        dc.setTitle("Choose drive");

        //Show open file dialog

        File file = dc.showDialog(null);

       if(file!=null){
            do_recover(file.getPath().substring(0, 1));
       }
		
	}
	
	private void do_recover(String driveLetter) {
		
		c1 = new Recovery(driveLetter + ":\\");
		String recoverError = c1.getErrorCode();
		if (!(recoverError == "")) {
			// There was an error
		} else {
			System.out.println("Dat file exists: " + c1.getDatFileExists());
	        System.out.println("Version Name: " + c1.getVersionName());
	        System.out.println("Version Year: " + c1.getVersionYear());
	        System.out.println("License Number: " + c1.getLicenseNum());
	        System.out.println("Product Number: " + c1.getProductNum());
	        
	        lblVersionName.setText(c1.getVersionName());
	        lblVersionYear.setText(c1.getVersionYear().toString());
	        txtLicenseNum.setText(c1.getLicenseNum());
	        txtProdNum.setText(c1.getProductNum());
	        
	        // Change status to success
	        lblStatus.setText("QuickBooks information recovered successfully");
		}
	}
}
