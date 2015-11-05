package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.LandingSet;
import controller.Controller;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Landing Gear System");

        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("fxml/sample.fxml").openStream());
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}