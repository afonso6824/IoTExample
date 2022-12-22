package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.eventos.OpenDoorEvent;

public class AnalizaPorta {

    public AnalizaPorta() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Porta Zirk");
        System.err.println("Got Bezirk instance");
        final EventSet openDoorEvents = new EventSet(OpenDoorEvent.class);

        openDoorEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
                public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof OpenDoorEvent) {
                    final OpenDoorEvent doorEvent = (OpenDoorEvent) event;
                    System.err.println("\nReceived air quality update: " + doorEvent.toString());
                    //do something
                    //TODO enviar mensagem
                }
            }
        });
        bezirk.subscribe(openDoorEvents);
    }

    public static void main(String[] args) {
        new AnalizaPorta();
        System.err.println("This product has an Analiza Porta");

    }

}
