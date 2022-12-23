package componentes.atuadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.eventos.DoorUnlockedEvent;


public class Fechadura {
    private final FechaduraController fechaduraController;

    public Fechadura(){
        this(1234);
    }
    public Fechadura(int code){
        BezirkMiddleware.initialize();
        Bezirk bezirk = BezirkMiddleware.registerZirk("Lock Zirk");
        System.err.println("Got Bezirk instance");
        fechaduraController = new FechaduraController(code);

        final EventSet doorUnlockedEvents = new EventSet(DoorUnlockedEvent.class);

        doorUnlockedEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof DoorUnlockedEvent) {
                    final DoorUnlockedEvent doorUnlockedEvent = (DoorUnlockedEvent) event;
                    System.err.println("\nReceived air quality update: " + doorUnlockedEvent.toString());
                    fechaduraController.openDoor();
                    System.out.println(fechaduraController.toString());
                }
            }
        });
        //todo fechar porta
    }

    @Override
    public String toString() {
        return fechaduraController.toString();
    }

    public static void main(String[] args) {
        Fechadura fechadura = new Fechadura();
        System.out.println(fechadura.toString());
    }
}
