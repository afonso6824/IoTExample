package componentes.atuadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import utils.I18N;
import utils.Messages;
import utils.eventos.LightsOFFEvent;

import static utils.Messages.LAMP_OFF;
import static utils.Messages.lAMP_ANNOUNCEMENT;

public class Lampada {

    private final LampadaController lampadaController;

    public Lampada(){
        BezirkMiddleware.initialize();
        Bezirk bezirk = BezirkMiddleware.registerZirk("Lock Zirk");
        System.err.println("Got Bezirk instance");
        lampadaController = new LampadaController();

        final EventSet lightsEvents = new EventSet(LightsOFFEvent.class);

        lightsEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof LightsOFFEvent) {
                    final LightsOFFEvent lightsOFFEvent = (LightsOFFEvent) event;
                    lampadaController.turnOff();

                }
            }
        });
    }

    @Override
    public String toString() {
        return lampadaController.toString();
    }

    public static void main(String[] args) {
        Lampada lampada = new Lampada();
        System.out.println(I18N.getString(lAMP_ANNOUNCEMENT));
    }
}
