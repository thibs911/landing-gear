package service;

import com.google.common.collect.Lists;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.LandingSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thread.LandingThread;

import java.util.List;
import java.util.Observable;

/**
 * Created by thibs911hotmail.com on 22/10/2015.
 */

/**
 * DigitalPart est un observable, elle permet de gérer les 3 threads des landingSet
 * Elle vérifie la position de la manette pour savoir qu'elle procédure l'utilisateur souhaite réaliser
 */
public class DigitalPart extends Observable implements ChangeListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DigitalPart.class);

    private boolean isHandleUp;
    private LandingThread landingSetFront, landingSetRight, landingSetLeft;


    private List<LandingThread> landingThreadList;

    /**
     * On instancie lors du constructeur les 3 threads qui contiennet chacun un LandingSet
     */
    public DigitalPart() {

        this.landingSetFront = new LandingThread(new LandingSet("landingSetFront"));
        this.landingSetLeft = new LandingThread(new LandingSet("landingSetLeft"));
        this.landingSetRight = new LandingThread(new LandingSet("landingSetRight"));

        this.landingThreadList = Lists.newArrayList(landingSetFront, landingSetLeft, landingSetRight);

        LOGGER.debug("Creation of  {} LandingSet {} : ", landingThreadList.size(), landingThreadList);
    }

    /**
     * On récupère ici la valeur de boutonSwitch pour savoir si il est position UP ou DOWN
     * @param observable
     * @param oldValue
     * @param newValue
     */
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {

        LOGGER.debug("old : {} || new : {}", oldValue, newValue);

        Boolean handleState = Boolean.valueOf(newValue.toString());

        /**
         * Cas où la manette est en position Down
         */
        if (handleState != true) {

            LOGGER.debug("Procédure d'extractation des Gears enclenchée");

            if (!checkIfGearIsExtracted()) {
                for (LandingThread thread : landingThreadList) {
                    thread.setMovement(true);
                    checkIfThreadAlreadyStarted(thread);
                }
            }
        } else {
            /**
             * Manette en position UP, on vérifie que les roues sont bien extraites en premier
             */
            LOGGER.debug("Procédure de rétractation des Gears enclenchée");

            if (checkIfGearIsExtracted()) {

                for (LandingThread thread : landingThreadList) {
                    thread.setMovement(false);
                    checkIfThreadAlreadyStarted(thread);
                }

            } else if (checkIfGearIsExtracted() == null) {

            }

        }
    }

    /**
     * Vérifie si le thread d'un gear a déjà été démarré (exemple lors de l'initialisation)
     * @return
     */
    protected Boolean checkIfGearIsExtracted() {
        Boolean check = false;
        for (LandingThread landingThread : landingThreadList) {
            check = landingThread.getLandingSet().getCurrentState();
            if (!check) {
                return false;
            } else if (check == null) {
                return null;
            }
        }
        LOGGER.debug("checkIfGearIsExtracted {}", check);

        return check;
    }

    public List<LandingSet> getLandingSetList() {
        List<LandingSet> landingSetList = Lists.newArrayList();
        for (LandingThread thread : landingThreadList) {
            landingSetList.add(thread.getLandingSet());
        }
        return landingSetList;
    }

    public void checkIfThreadAlreadyStarted(LandingThread thread){
        if(thread.currentThread().isAlive()){
            thread.run();
        }else{
            thread.start();
        }
    }
}
