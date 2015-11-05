package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import service.DigitalPart;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class Controller implements Initializable, Observer{
    @FXML private ImageView gear1;
    @FXML private ImageView gear2;
    @FXML private ImageView gear3;
    @FXML private ImageView lights;
    @FXML private ImageView door1;
    @FXML private ImageView door2;
    @FXML private ImageView door3;
    @FXML private GridPane controlGrid;

    public DigitalPart digitalPart;
    public ToggleSwitch switchButton;

    public void initialize(URL location, ResourceBundle resources) {

        this.digitalPart = new DigitalPart();

        Image closedDoor = new Image("img/door2_closed.jpg");
        Image closedGear = new Image("/img/gear2_extracted.jpg");
        Image light = new Image("/img/feu_vert.jpg");

        ImageViewSettings(closedDoor,door1);

        ImageViewSettings(closedDoor,door2);
        ImageViewSettings(closedDoor,door3);
        ImageViewSettings(closedGear,gear1);
        ImageViewSettings(closedGear,gear2);
        ImageViewSettings(closedGear,gear3);
        ImageViewSettings(light,lights);

        switchButton = new ToggleSwitch();
        switchButton.setId("switchBtn");
        switchButton.setMaxSize(200,50);
        controlGrid.add(switchButton, 0, 0);
        controlGrid.setValignment(switchButton, VPos.CENTER);
        controlGrid.setHalignment(switchButton, HPos.CENTER);

        digitalPart.addObserver(this);
        switchButton.addListener(digitalPart);
    }

    private void ImageViewSettings(Image img, ImageView imv)
    {
        imv.setImage(img);
        imv.setFitWidth(200);
        imv.setPreserveRatio(true);
        imv.setSmooth(true);
        imv.setCache(true);
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}

