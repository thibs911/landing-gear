package model;

/**
 * Created by thibs911hotmail.com on 22/10/2015.
 */

/**
 * Correspond au train d'atterissage d'un LandingSet
 */
public class LandingGear {


    /**
     * sensor permet de savoir si les roues sont sorties ou pas (true = roues sorties / false = roues rentrées)
     */
    private boolean sensor;

    /**
     * Lors de l'initialisation, on considère que l'avion est au sol, les roues sont donc sorties)
     */
    public LandingGear() {
        this.sensor = true;
    }

    public boolean isSensor() {
        return sensor;
    }

    public void setSensor(boolean sensor) {
        this.sensor = sensor;
    }
}
