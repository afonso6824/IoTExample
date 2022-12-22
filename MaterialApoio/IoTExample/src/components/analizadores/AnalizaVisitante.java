package components.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import components.sensores.DoorSensorZirk;
import components.sensores.BellDetectorZirk;

import utils.eventos.ButtonPressedEvent;
import utils.eventos.OpenDoorEvent;
import utils.eventos.BellRungEvent;

import utils.I18N;
import static utils.Messages.*;

public class AnalizaVisitante {
    public AnalizaVisitante() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Visitante Zirk");
        System.err.println("Got Bezirk instance");
        //todo
        final EventSet openDoorEvents = new EventSet(OpenDoorEvent.class);
        final EventSet bellRungEvents = new EventSet(BellRungEvent.class);

       /* buttonPressedEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof ButtonPressedEvent) {
                    final ButtonPressedEvent buttonEvent = (ButtonPressedEvent) event;
                    System.err.println("\nReceived air quality update: " + buttonEvent.toString());
                    //do something
                    //TODO ver se apos toque campainha se porta aberta no periodo definido e enviar mensagem exterior


                }
            }
        });*/
        bezirk.subscribe(openDoorEvents);
        bezirk.subscribe(bellRungEvents);
    }




    public static void main(String args[]) {
        new AnalizaVisitante();
        System.err.println("This product has an Analiza Visitante");
    }

}
