package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.I18N;
import utils.eventos.BellRungEvent;
import utils.eventos.RingSirenEvent;
import utils.eventos.SendWarningEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static utils.Messages.*;


public class AnalizaCampainha {
    public AnalizaCampainha() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Campainha Zirk");
        System.err.println(ZIRK_INSTANCE);
        final EventSet bellRungEvents = new EventSet(BellRungEvent.class);

        bellRungEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof BellRungEvent) {
                    final BellRungEvent bellEvent = (BellRungEvent) event;

                    //do something
                    if (hasSiren()){
                        RingSirenEvent ringSirenEvent = new RingSirenEvent();
                        bezirk.sendEvent(ringSirenEvent);
                    }else {
                        SendWarningEvent sendWarningEvent = new SendWarningEvent("Campainha tocada");
                        bezirk.sendEvent(sendWarningEvent);
                    }

                }
            }
        });
        bezirk.subscribe(bellRungEvents);
    }

    private boolean hasSiren(){
            boolean hasSiren= false;
            String configFilePath = "src/utils/config.properties";
            try {
                FileInputStream propsInput = new FileInputStream(configFilePath);
                Properties prop = new Properties();
                prop.load(propsInput);
                String property = prop.getProperty("APARELHOS");
                String[] props = property.split(";");
                for (String s: props) {
                    hasSiren = hasSiren || s.equals("SIRENE");
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return hasSiren;

    }


   public static void main(String args[]) {
        new AnalizaCampainha();
       System.out.println(I18N.getString(BELL_ANALYZER_ANNOUNCEMENT));
    }

}
