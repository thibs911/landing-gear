package sample;

import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class Controller extends Observable implements Initializable{
    @FXML private ImageView gear1;
    @FXML private ImageView gear2;
    @FXML private ImageView gear3;
    @FXML private ImageView lights;
    @FXML private ImageView door1;
    @FXML private ImageView door2;
    @FXML private ImageView door3;


    public void initialize(URL location, ResourceBundle resources) {

        Image gearImage = new Image("/img/gear2_retracted.jpg");
        Image lightImage = new Image("/img/feu_vide.jpg");
        Image doorImage = new Image("/img/door2_closed.jpg");

        ImageViewSettings(lightImage,lights);

        ImageViewSettings(gearImage,gear1);
        ImageViewSettings(gearImage,gear2);
        ImageViewSettings(gearImage,gear3);

        ImageViewSettings(doorImage,door1);
        ImageViewSettings(doorImage,door2);
        ImageViewSettings(doorImage,door3);
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

