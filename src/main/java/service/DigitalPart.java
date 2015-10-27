package service;

import thread.LandingThread;

/**
 * Created by thibs911hotmail.com on 22/10/2015.
 */
public class DigitalPart{

private boolean isHandleUp;

   public void main(){
     if(!isHandleUp){
         LandingThread landingSet1 = new LandingThread();
         landingSet1.start();
     }
   }
}
