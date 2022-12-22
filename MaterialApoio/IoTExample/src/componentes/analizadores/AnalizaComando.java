package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.eventos.ButtonPressedEvent;

public class AnalizaComando {

    public AnalizaComando() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Comando Zirk");
        System.err.println("Got Bezirk instance");
        final EventSet buttonPressedEvents = new EventSet(ButtonPressedEvent.class);

        buttonPressedEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof ButtonPressedEvent) {
                    final ButtonPressedEvent buttonEvent = (ButtonPressedEvent) event;
                    System.err.println("\nReceived air quality update: " + buttonEvent.toString());
                    //do something
                    //TODO case 1 mensagem exterior e aviso
                    //TODO case 2 comando + lampadas apagar luzes
                    //todo case 3 comando + sirene ativar sirene
                    //todo case 4 comando + fechadura abrir porta

                }
            }
        });
        bezirk.subscribe(buttonPressedEvents);
    }




    public static void main(String args[]) {
        new AnalizaComando();
        System.err.println("This product has an Analiza Campainha");
    }

}
