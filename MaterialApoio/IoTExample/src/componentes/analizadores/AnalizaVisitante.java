package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.eventos.OpenDoorEvent;
import utils.eventos.BellRungEvent;

public class AnalizaVisitante {
    public AnalizaVisitante() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Visitante Zirk");
        System.err.println("Got Bezirk instance");
        //todo
        final EventSet openDoorEvents = new EventSet(OpenDoorEvent.class);
        final EventSet bellRungEvents = new EventSet(BellRungEvent.class);

        bellRungEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof BellRungEvent) {
                    final BellRungEvent bellEvent = (BellRungEvent) event;
                    System.err.println("\nReceived air quality update: " + bellEvent.toString());
                    openDoorEvents.setEventReceiver(new EventSet.EventReceiver() {
                        @Override
                        public void receiveEvent(Event event, ZirkEndPoint sender) {
                            if (event instanceof OpenDoorEvent) {
                                final OpenDoorEvent doorEvent = (OpenDoorEvent) event;
                                //do something
                                //TODO
                            }
                        }});
                                                    }

                }
            });
        bezirk.subscribe(openDoorEvents);
    }




    public static void main(String args[]) {
        new AnalizaVisitante();
        System.err.println("This product has an Analiza Visitante");
    }

}
