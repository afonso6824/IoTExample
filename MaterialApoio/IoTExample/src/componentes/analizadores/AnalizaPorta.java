package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.eventos.CloseDoorEvent;
import utils.eventos.OpenDoorEvent;
import utils.eventos.SendWarningEvent;

import java.sql.Time;

public class AnalizaPorta {
    private Time periodo;
    private Bezirk bezirk;
    public AnalizaPorta() {
        //TODO VER PERIODO E ADICIONAR EVENTO
        BezirkMiddleware.initialize();
         bezirk = BezirkMiddleware.registerZirk("Analiza Porta Zirk");
        System.err.println("Got Bezirk instance");
        final EventSet openDoorEvents = new EventSet(OpenDoorEvent.class, CloseDoorEvent.class);

        openDoorEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            //todo ver como fazer a parte de esperar
                public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof OpenDoorEvent) {
                    final OpenDoorEvent doorEvent = (OpenDoorEvent) event;
                    System.err.println("\nReceived air quality update: " + doorEvent.toString());
                    //do something
                    SendWarningEvent sendWarningEvent = new SendWarningEvent("porta aberta Ha pelo menos x tempo");
                    bezirk.sendEvent(sendWarningEvent);

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
