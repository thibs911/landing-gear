package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by thibs911hotmail.com on 22/10/2015.
 */

/**
 * Un landingSet est considéré comme l'ensemble du train d'atterrissage (landingGear) + les volets (Door)
 * LandingSet est en Observable avec le Controller, et notifie à ce dernier lors d'un changement de Statut
 * (mouvement, fermeture, ...)
 */
public class LandingSet extends Observable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LandingSet.class);
    
    private LandingGear landingGear;
    private Door door;
    private Status status;
    private String name;

    /**
     * Set le Statut actuel du LandingGear et notifie le controller qui est Observer
     * @param status
     */
    public void setStatus(Status status){
        this.status=status;
        setChanged();
        notifyObservers();
    }

    public Status getStatus() {
        return status;
    }

    public LandingSet(String name) {
        this.landingGear = new LandingGear();
        this.door = new Door();
        this.name = name;
    }

    /**
     * Permet de savoir si le landingGear est extrait ou rétracté
     * @return
     */
    public Boolean getCurrentState(){
        if(!this.door.isSensor()){
            if(this.landingGear.isSensor()){
                return true;
            }else{
                return false;
            }
        }else{
            return null;
        }
    }

    /**
     * Fonction qui démarre la procédure de rétractation des gears
     */
    public void retractGear(){
        LOGGER.debug("{} : rétractation du Gear", name);
        openDoorsAndRetractGear();
    }

    /**
     * Fonction qui démarre la procédure d'extraction des gears
     */
    public void extractGear(){
        LOGGER.debug("{} : extraction du Gear", name);
        openDoorsAndExtractGear();
    }

    /**
     * Cette fonction à l'aide de Timer (pour espacer dans le temps les étapes) réalise les différentes étapes de
     * la procédure de rétractation
     * Ouverture des portes, remontée du train d'atterissage, fermetures des portes.
     * On notifie à chaque l'observer à chaque étaê
     */
    public void openDoorsAndRetractGear(){
        LOGGER.debug("{} : Portes en mouvement", name);
        setStatus(Status.GLOBAL_MOVEMENT);
        setStatus(Status.DOOR_IN_MOVEMENT);

        Timer firstStepTimer = new Timer();
        firstStepTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                LOGGER.debug("{} : Portes ouvertes", name);
                setStatus(Status.DOOR_OPENED);
                setDoorOpenClose(true);

                Timer secondStepTimer = new Timer();
                secondStepTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        LOGGER.debug("{} : Gear en mouvement", name);
                        setStatus(Status.GEAR_IN_MOVEMENT);

                        Timer thirdStepTimer = new Timer();
                        thirdStepTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                LOGGER.debug("{} : Gear retracted", name);
                                setGearExtractRetract(false);
                                setStatus(Status.RETRACTED);
                                Timer fourthStepTimer = new Timer();
                                fourthStepTimer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        LOGGER.debug("{} : Portes en mouvement", name);
                                        setStatus(Status.DOOR_IN_MOVEMENT);

                                        Timer lastStepTimer = new Timer();
                                        lastStepTimer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                LOGGER.debug("{} : Portes fermées", name);
                                                setStatus(Status.DOOR_CLOSED);
                                                setStatus(Status.SUCCESS);
                                                setDoorOpenClose(false);
                                            }
                                        }, 2000);
                                    }
                                }, 2000);

                            }
                        }, 5000);
                    }
                }, 2000);
            }
        }, 2000);
    }

    /**
     * Cette fonction à l'aide de Timer (pour espacer dans le temps les étapes) réalise les différentes étapes de
     * la procédure d'extraction
     * Ouverture des portes, extraction du train d'atterissage, fermetures des portes.
     * On notifie à chaque l'observer à chaque étaê
     */
    public void openDoorsAndExtractGear(){
        LOGGER.debug("{} : Portes en mouvement", name);
        setStatus(Status.GLOBAL_MOVEMENT);
        setStatus(Status.DOOR_IN_MOVEMENT);

        Timer firstStepTimer = new Timer();
        firstStepTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                LOGGER.debug("{} : Portes ouvertes", name);

                setStatus(Status.DOOR_OPENED);
                setDoorOpenClose(true);

                Timer secondStepTimer = new Timer();
                secondStepTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        LOGGER.debug("{} : Gear en mouvement", name);
                        setStatus(Status.GEAR_IN_MOVEMENT);

                        Timer thirdStepTimer = new Timer();
                        thirdStepTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                LOGGER.debug("{} : Gear extracted", name);
                                setGearExtractRetract(true);
                                setStatus(Status.EXTRACTED);

                                Timer fourthStepTimer = new Timer();
                                fourthStepTimer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        LOGGER.debug("{} : Portes en mouvement", name);
                                        setStatus(Status.DOOR_IN_MOVEMENT);

                                        Timer lastStepTimer = new Timer();
                                        lastStepTimer.schedule(new TimerTask() {
                                            @Override
                                            public void run() {
                                                LOGGER.debug("{} : Portes fermées", name);
                                                setStatus(Status.DOOR_CLOSED);
                                                setStatus(Status.SUCCESS);
                                                setDoorOpenClose(false);
                                            }
                                        }, 2000);
                                    }
                                }, 2000);

                            }
                        }, 5000);
                    }
                }, 2000);
            }
        }, 2000);

    }

    public String getName() {
        return name;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public LandingGear getLandingGear() {
        return landingGear;
    }

    public void setLandingGear(LandingGear landingGear) {
        this.landingGear = landingGear;
    }
    
    public void setDoorOpenClose(boolean boo){
        this.door.setSensor(boo);
    }
    
    public void setGearExtractRetract(boolean boo){
        this.landingGear.setSensor(boo);
    }
}
