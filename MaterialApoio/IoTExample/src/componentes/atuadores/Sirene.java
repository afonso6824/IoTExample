package componentes.atuadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import utils.eventos.RingSirenEvent;

public class Sirene {

    private final SireneController sireneController;

    public Sirene() {
        BezirkMiddleware.initialize();
        Bezirk bezirk = BezirkMiddleware.registerZirk("Lock Zirk");
        System.err.println("Got Bezirk instance");
        sireneController = new SireneController();

        final EventSet ringSireneEvents = new EventSet(RingSirenEvent.class);

        ringSireneEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof RingSirenEvent) {
                    final RingSirenEvent ringSirenEvent = (RingSirenEvent) event;
                    System.err.println("\nReceived air quality update: " + ringSirenEvent.toString());
                    sireneController.ring();
                }
            }
        });
        //todo turn off
    }
        public static void main(String[] args) {
            Sirene sirene = new Sirene();

    }
}
