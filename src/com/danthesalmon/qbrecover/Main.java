package com.danthesalmon.qbrecover;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.show();
        */
    }


    public static void main(String[] args) {
        //launch(args);
        Controller c1 = new Controller("C");

        //System.out.println("Dat file exists: " + c1.getDatFileExists());
        //System.out.println("OS: " + c1.getOS());
        System.out.println("Version Name: " + c1.getVersionName());
        System.out.println("Version Year: " + c1.getVersionYear());
        System.out.println("License Number: " + c1.getLicenseNum());
        System.out.println("Product Number: " + c1.getProductNum());
    }
}
