package componentes.atuadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import utils.eventos.LightsOFFEvent;

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
                    System.err.println("\nReceived air quality update: " + lightsEvents.toString());
                    lampadaController.turnOff();
                    System.out.println(lampadaController.toString());
                }
            }
        });
        //todo turn on
    }

    @Override
    public String toString() {
        return lampadaController.toString();
    }

    public static void main(String[] args) {
        Lampada lampada = new Lampada();
        System.out.println(lampada.toString());
    }
}
