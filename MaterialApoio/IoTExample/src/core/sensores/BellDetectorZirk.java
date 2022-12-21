package core.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import i18n.I18N;

import static i18n.Messages.DEVICE_RUNNING;

public class BellDetectorZirk {
    private Bezirk bezirk;

    //TODO ver parte dos aspects na lingua, ou seja como por as coisas a mandarem emnsagens no idioma escolhido
    public BellDetectorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Bell Detector Zirk");
        System.err.println("Got Bezirk instance");
    }

    /*public void sendAirQualityUpdate() {
    	//produces some  values since this is a mock
        final double humidity = 0.8;
        final int dustLevel = 30;
        final int pollenLevel = 1000;
        final AirQualityUpdateEvent airQualityUpdateEvent = new AirQualityUpdateEvent(humidity, dustLevel, pollenLevel);

        //sends the event
        bezirk.sendEvent(airQualityUpdateEvent);
        System.err.println("Published air quality update: " + airQualityUpdateEvent.toString());
    }

    public void sendPeriodiclyAirQualityUpdate(){
    	//publish messages periodically; also a mock but more sophisticated
        new Timer().scheduleAtFixedRate(new TimerTask() {
            private int pollenLevel = 400;
            private double humidity = 1.0;
            private int dustLevel = 17;
 
            @Override
            public void run() {
            	pollenLevel += 10;
            	humidity = humidity > 0.4 ? humidity-0.1 : humidity;
            	dustLevel++;
                AirQualityUpdateEvent airQualityUpdateEvent = 
                		new AirQualityUpdateEvent(humidity,dustLevel,pollenLevel); 
                bezirk.sendEvent(airQualityUpdateEvent);
            }
        }, 1000, 1000);
    }
    */
    public static void main(String args[]) throws InterruptedException {
        BellDetectorZirk bellDetectorZirk = new BellDetectorZirk();
        System.err.println("This product has an Bell Detector");
        
        System.err.println(I18N.getString(DEVICE_RUNNING, "Bell Detector"));
        // airQualitySensorZirk.sendAirQualityUpdate();
        // airQualitySensorZirk.sendPeriodiclyAirQualityUpdate();
     }
}