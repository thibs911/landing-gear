package model;

/**
 * Created by thibs911hotmail.com on 22/10/2015.
 */
public class LandingSet {

    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean stateGear, boolean stateDoor) {
        if(stateGear && stateDoor){
            this.state = true;
        }else{
            this.state = false;
        }
    }
}
