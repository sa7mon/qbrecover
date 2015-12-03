package com.danthesalmon.qbrecover.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.danthesalmon.qbrecover.controller.Controller;
import com.danthesalmon.qbrecover.model.Recovery;

public class View extends Application{
	
	//-------------------- GUI Elements ----------------------
	public static Stage primaryStage;
	// Must match the pane type in the .fxml
    private AnchorPane rootLayout;
    
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.getIcons().add(new Image("/com/danthesalmon/qbrecover/view/qbrecover-logo.png"));
	    this.primaryStage.setTitle("qbrecover - Recover QuickBooks License");
	    initRootLayout();
		
	}
	
    public void initRootLayout() {
      try {
         // Load layout from .fxml file
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(View.class.getResource("/com/danthesalmon/qbrecover/view/MainView.fxml"));
         rootLayout = (AnchorPane) loader.load();
         
         //Give controller access to the main app
         Controller controller = loader.getController();
         
         // Show scene containing the root layout
         Scene scene = new Scene(rootLayout);
         primaryStage.setScene(scene);
         primaryStage.show();
         
      } catch(IOException e) { 
    	  e.printStackTrace();
      }
   }
		   
    public Stage getPrimaryStage () {
      return primaryStage;
   }
		   
	public static void main(String[] args) {
		launch(args);
	}

}
