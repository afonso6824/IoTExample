package componentes.atuadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.I18N;
import utils.Messages;
import utils.eventos.DoorUnlockedEvent;

import static utils.Messages.LOCK_ANNOUNCEMENT;


public class Fechadura {
    private final FechaduraController fechaduraController;

    public Fechadura(){
        this(1234);
    }
    public Fechadura(int code){
        BezirkMiddleware.initialize();
        Bezirk bezirk = BezirkMiddleware.registerZirk("Lock Zirk");
        //todo mudar para aspect todos os got bezirk
        System.err.println("Got Bezirk instance");
        fechaduraController = new FechaduraController(code);

        final EventSet doorUnlockedEvents = new EventSet(DoorUnlockedEvent.class);

        doorUnlockedEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof DoorUnlockedEvent) {
                    fechaduraController.openDoor();
                }
            }
        });
    }

    @Override
    public String toString() {
        return fechaduraController.toString();
    }

    public static void main(String[] args) {
        Fechadura fechadura = new Fechadura();
        System.out.println(I18N.getString(LOCK_ANNOUNCEMENT));
    }
}
