package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.LandingSet;
import java.io.IOException;

public class Main extends Application {
    private Controller control;
    private LandingSet model;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Landing Gear System");
        System.out.println("toto");
        model = new LandingSet();
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.println("toto");
        try{
            Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("fxml/sample.fxml").openStream());
            control = fxmlLoader.getController();
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