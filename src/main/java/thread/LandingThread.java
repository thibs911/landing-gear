package thread;

import model.LandingSet;

/**
 * Created by thibs911hotmail.com on 27/10/2015.
 */
public class LandingThread extends Thread {

    private LandingSet landingSet;

    public LandingThread(LandingSet landingSet) {
        this.landingSet = landingSet;
    }

    /**
     * movement permet de savoir qu'elle ordre faire passer aux hydrauliques : false = on veut remonter les roues
     * true = on veut descendre les roues
     */
    private boolean movement;

    public void run(){
        if(movement){
            landingSet.extractGear();
        }else{
            landingSet.retractGear();
        }
    }

    public LandingSet getLandingSet() {
        return landingSet;
    }

    public void setMovement(boolean movement) {
        this.movement = movement;
    }
}
