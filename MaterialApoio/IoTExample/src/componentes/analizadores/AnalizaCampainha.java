package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.eventos.BellRungEvent;


public class AnalizaCampainha {
    public AnalizaCampainha() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Campainha Zirk");
        System.err.println("Got Bezirk instance");
        final EventSet bellRungEvents = new EventSet(BellRungEvent.class);

        bellRungEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof BellRungEvent) {
                    final BellRungEvent bellEvent = (BellRungEvent) event;
                    System.err.println("\nReceived air quality update: " + bellEvent.toString());
                    //do something
                    //TODO se tem sirene faz soar sirene else aviso

                }
            }
        });
        bezirk.subscribe(bellRungEvents);
    }




   public static void main(String args[]) {
        new AnalizaCampainha();
        System.err.println("This product has an Analiza Campainha");
    }

}
