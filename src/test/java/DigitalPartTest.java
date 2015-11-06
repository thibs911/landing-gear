import junit.framework.Assert;
import model.LandingSet;
import org.junit.Test;
import service.DigitalPart;
import static org.junit.Assert.*;

/**
 * Created by thibs911hotmail.com on 04/11/2015.
 */
public class DigitalPartTest {


    @Test
    public void extractGearAndDoors() throws InterruptedException {
        DigitalPart digitalPart = new DigitalPart();
        LandingSet landingSet =  digitalPart.getLandingSetList().get(0);
        landingSet.getDoor().setSensor(false);
        landingSet.getLandingGear().setSensor(false);
        assertEquals(false, landingSet.getCurrentState());

        landingSet.openDoorsAndExtractGear();
        Thread.sleep(15000);
        assertEquals(true, landingSet.getCurrentState());

    }

    @Test
    public void retractGearAndDoors() throws InterruptedException {

        DigitalPart digitalPart = new DigitalPart();

        LandingSet landingSet =  digitalPart.getLandingSetList().get(0);


        assertEquals(true, landingSet.getCurrentState());

        landingSet.openDoorsAndRetractGear();
        Thread.sleep(15000);
        assertEquals(false, landingSet.getCurrentState());


    }
}
