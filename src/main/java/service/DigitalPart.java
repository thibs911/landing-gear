package service;

import com.google.common.collect.Lists;
import model.LandingSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thread.LandingThread;

import java.util.List;
import java.util.Observable;

/**
 * Created by thibs911hotmail.com on 22/10/2015.
 */
public class DigitalPart extends Observable{

    private static final Logger LOGGER = LoggerFactory.getLogger(DigitalPart.class);

    private boolean isHandleUp;
    private LandingSet landingSetFront, landingSetRight, landingSetLeft;
    private List<LandingSet> landingSetList;

    public DigitalPart() {

        this.landingSetFront = new LandingSet();
        this.landingSetLeft = new LandingSet();
        this.landingSetRight = new LandingSet();

        this.landingSetList = Lists.newArrayList(landingSetFront, landingSetLeft, landingSetRight);

        LOGGER.debug("Création des {} trains d'atterissage {} : ", landingSetList.size(), landingSetList);


    }

    public void main(){
     if(!isHandleUp){
         LandingThread landingSet1 = new LandingThread();
         landingSet1.start();
     }
   }
}
