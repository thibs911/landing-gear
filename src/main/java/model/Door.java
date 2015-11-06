package model;

/**
 * Created by thibs911hotmail.com on 22/10/2015.
 */

/**
 * Correspond au volet du LandingSet
 */
public class Door {

    /**
     * sensor permet de savoir si les portes sont fermées ou pas (false = porte fermée / true = porte ouverte)
     */
    private boolean sensor;

    /**
     * Lors de l'initialisation de la porte, on considère que l'avion est au sol, les portes sont donc fermées
     */
    public Door() {
        this.sensor = false;
    }

    public boolean isSensor() {
        return sensor;
    }

    public void setSensor(boolean sensor) {
        this.sensor = sensor;
    }
}
