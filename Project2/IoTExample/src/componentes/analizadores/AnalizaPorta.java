package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.I18N;
import utils.eventos.CloseDoorEvent;
import utils.eventos.OpenDoorEvent;
import utils.eventos.SendWarningEvent;

import java.sql.Time;

import static utils.Messages.*;

public class AnalizaPorta {
    private Time periodo;
    private Bezirk bezirk;
    public AnalizaPorta() {
        BezirkMiddleware.initialize();
         bezirk = BezirkMiddleware.registerZirk("Analiza Porta Zirk");
        System.err.println(ZIRK_INSTANCE);
        final EventSet openDoorEvents = new EventSet(OpenDoorEvent.class, CloseDoorEvent.class);

        openDoorEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override

                public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof OpenDoorEvent) {
                    final OpenDoorEvent doorEvent = (OpenDoorEvent) event;
                    SendWarningEvent sendWarningEvent = new SendWarningEvent("porta aberta Ha pelo menos x tempo");
                    bezirk.sendEvent(sendWarningEvent);

                }
            }
        });
        bezirk.subscribe(openDoorEvents);
    }

    public static void main(String[] args) {
        new AnalizaPorta();
        System.out.println(I18N.getString(DOOR_ANALYZER_ANNOUNCEMENT));

    }

}
