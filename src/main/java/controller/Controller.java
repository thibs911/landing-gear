package controller;

import com.google.common.collect.Lists;
import exception.LandingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.LandingSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.DigitalPart;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class Controller implements Initializable, Observer{

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);


    @FXML private ImageView gear1;
    @FXML private ImageView gear2;
    @FXML private ImageView gear3;
    @FXML private ImageView lights;
    @FXML private ImageView door1;
    @FXML private ImageView door2;
    @FXML private ImageView door3;
    @FXML private GridPane controlGrid;

    private Image closedDoor;
    private Image closedGear;
    private Image lightEmpty;
    private Image openedDoor;
    private Image openGear;
    private Image lightG;
    private Image movingDoor;
    private Image movingGear;
    private Image lightR;
    private Image lightO;

    private List<LandingSet> landingSetList = Lists.newArrayList();

    public DigitalPart digitalPart;
    public ToggleSwitch switchButton;

    public void initialize(URL location, ResourceBundle resources) {

        this.digitalPart = new DigitalPart();

        closedDoor = new Image("img/door2_closed.jpg");
        closedGear = new Image("/img/gear2_retracted.jpg");
        lightEmpty = new Image("/img/feu_vide.jpg");
        openedDoor = new Image("img/door2_opened.jpg");
        openGear = new Image("/img/gear2_extracted.jpg");
        lightG = new Image("/img/feu_vert.jpg");
        movingDoor = new Image("img/door2_moving.jpg");
        movingGear = new Image("/img/gear2_moving.jpg");
        lightR = new Image("/img/feu_rouge.jpg");
        lightO = new Image("/img/feu_orange.jpg");

        ImageViewSettings(closedDoor,door1);
        ImageViewSettings(closedDoor,door2);
        ImageViewSettings(closedDoor,door3);
        ImageViewSettings(openGear,gear1);
        ImageViewSettings(openGear,gear2);
        ImageViewSettings(openGear,gear3);
        ImageViewSettings(lightEmpty,lights);

        switchButton = new ToggleSwitch();
        switchButton.setId("switchBtn");
        switchButton.setMaxSize(200,50);
        controlGrid.add(switchButton, 0, 0);
        controlGrid.setValignment(switchButton, VPos.CENTER);
        controlGrid.setHalignment(switchButton, HPos.CENTER);


        digitalPart.addObserver(this);
        switchButton.addListener(digitalPart);
        for (LandingSet landingSet : digitalPart.getLandingSetList()){
            landingSet.addObserver(this);
        }
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
        LandingSet landingSet = (LandingSet) o;
        switch(landingSet.getName()){
            case "landingSetFront" :
                try {
                    changeGearAndDoorMovement(landingSet, gear1, door1);
                } catch (LandingException e) {
                    LOGGER.error(e.getMessage());
                }
                break;
            case "landingSetRight" :
                try {
                    changeGearAndDoorMovement(landingSet, gear2, door2);
                } catch (LandingException e) {
                    LOGGER.error(e.getMessage());
                }
                break;
            case "landingSetLeft" :
                try {
                    changeGearAndDoorMovement(landingSet, gear3, door3);
                } catch (LandingException e) {
                    LOGGER.error(e.getMessage());
                }
                break;
            default:
                throw new IllegalArgumentException("LandingSet inconnu");
        }

    }

    public void changeImage(ImageView imageView, Image image){
        imageView.setImage(image);
    }

    public void changeGearAndDoorMovement(LandingSet landingSet, ImageView gear, ImageView door) throws LandingException {
        switch(landingSet.getStatus()){
            case DOOR_IN_MOVEMENT:
                changeImage(door, movingDoor);
                break;
            case DOOR_OPENED:
                changeImage(door, openedDoor);
                break;
            case DOOR_CLOSED:
                changeImage(door, closedDoor);
                break;
            case GEAR_IN_MOVEMENT:
                changeImage(gear, movingGear);
                break;
            case RETRACTED:
                landingSetList.add(landingSet);
                if(!landingSet.getLandingGear().isSensor())
                    changeImage(gear, closedGear);
                break;
            case EXTRACTED:
                landingSetList.add(landingSet);
                if(landingSet.getLandingGear().isSensor())
                changeImage(gear, openGear);
                    break;
            case GLOBAL_MOVEMENT:
                changeImage(lights, lightO);
                break;
            case SUCCESS:
                if(landingSetList.size() == 3){
                    if(checkIfOk()){
                        if(landingSetList.get(0).getLandingGear().isSensor()){
                            changeImage(lights, lightG);
                        }else{
                            changeImage(lights, lightEmpty);
                        }
                    }else{
                        changeImage(lights, lightR);
                        throw new LandingException("Erreur Système");
                    }
                    landingSetList.clear();
                }
                break;

            default: throw new IllegalArgumentException("Option non disponible");
        }

    }

    public boolean checkIfOk(){
        boolean flag = true;
        Boolean sensor = null;
        for(LandingSet currentLandingSet : landingSetList){
            if(sensor == null){
                sensor = currentLandingSet.getCurrentState();
            }else{
                if(sensor != currentLandingSet.getCurrentState()){
                    flag = false;
                }
            }
        }
        return flag;
    }
}

