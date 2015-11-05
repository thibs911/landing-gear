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
public class DigitalPart extends Observable implements ChangeListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DigitalPart.class);

    private boolean isHandleUp;
    private LandingThread landingSetFront, landingSetRight, landingSetLeft;
    private List<LandingThread> landingSetList;

    public DigitalPart() {

        this.landingSetFront = new LandingThread(new LandingSet());
        this.landingSetLeft = new LandingThread(new LandingSet());
        this.landingSetRight = new LandingThread(new LandingSet());

        this.landingSetList = Lists.newArrayList(landingSetFront, landingSetLeft, landingSetRight);

        LOGGER.debug("Creation of  {} LandingSet {} : ", landingSetList.size(), landingSetList);
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {

        LOGGER.debug("old : {} || new : {}", oldValue, newValue);

        Boolean handleState = Boolean.getBoolean(newValue.toString());

        /**
         * Cas où la manette est en position Down
         */
        if(handleState != true){

        }
    }
}
