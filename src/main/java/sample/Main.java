package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Landing Gear System");

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/sample.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (IOException e){

        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}