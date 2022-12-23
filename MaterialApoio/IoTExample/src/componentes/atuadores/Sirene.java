package componentes.atuadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import utils.I18N;
import utils.eventos.RingSirenEvent;

import static utils.Messages.*;

public class Sirene {

    private final SireneController sireneController;

    public Sirene() {
        BezirkMiddleware.initialize();
        Bezirk bezirk = BezirkMiddleware.registerZirk("Sirene Zirk");
        System.err.println(ZIRK_INSTANCE);
        sireneController = new SireneController();

        final EventSet ringSireneEvents = new EventSet(RingSirenEvent.class);

        ringSireneEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof RingSirenEvent) {
                    final RingSirenEvent ringSirenEvent = (RingSirenEvent) event;
                    sireneController.ring();
                    try {
                        this.wait(5000);
                        sireneController.stop();
                    } catch (InterruptedException e) {
                        System.err.println(I18N.getString(SIRENE_ERROR));
                    }
                }
            }
        });
        //todo turn off
    }
        public static void main(String[] args) {
            Sirene sirene = new Sirene();
            System.out.println(I18N.getString(SIRENE_ANNOUNCEMENT));
    }
}
