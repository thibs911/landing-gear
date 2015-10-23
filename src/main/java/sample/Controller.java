package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML private ImageView gear1;
    @FXML private ImageView gear2;
    @FXML private ImageView gear3;
    @FXML private ImageView lights;
    public void initialize(URL location, ResourceBundle resources) {

        Image closedDoor = new Image("/img/gear2_retracted.jpg");
        Image light = new Image("/img/feu_vide.jpg");

        ImageViewSettings(closedDoor,gear1);
        ImageViewSettings(closedDoor,gear2);
        ImageViewSettings(closedDoor,gear3);
        ImageViewSettings(light,lights);
    }

    private void ImageViewSettings(Image img, ImageView imv)
    {
        imv.setImage(img);
        imv.setFitWidth(200);
        imv.setPreserveRatio(true);
        imv.setSmooth(true);
        imv.setCache(true);
    }

}

