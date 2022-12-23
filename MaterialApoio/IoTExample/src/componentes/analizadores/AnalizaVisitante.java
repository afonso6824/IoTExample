package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.eventos.OpenDoorEvent;
import utils.eventos.BellRungEvent;
import utils.eventos.SendMessageEvent;

import java.sql.Time;
import java.util.Date;

public class AnalizaVisitante {
    //todo periodo
    private Time periodo;
    public AnalizaVisitante() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Visitante Zirk");
        System.err.println("Got Bezirk instance");

        final EventSet openDoorEvents = new EventSet(OpenDoorEvent.class,BellRungEvent.class);


        openDoorEvents.setEventReceiver(new EventSet.EventReceiver() {
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
                                if (new Date().after(periodo)){
                                    SendMessageEvent sendMessageEvent = new SendMessageEvent("Visitante detetado");
                                    bezirk.sendEvent(sendMessageEvent);
                                }
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
