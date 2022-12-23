package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.I18N;
import utils.eventos.BellRungEvent;

import static utils.Messages.BELL_ANALYZER_ANNOUNCEMENT;
import static utils.Messages.COMMAND_ANALYZER_ANNOUNCEMENT;


public class AnalizaCampainha {
    public AnalizaCampainha() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Campainha Zirk");
        System.err.println("Got Bezirk instance");
        final EventSet bellRungEvents = new EventSet(BellRungEvent.class);

        bellRungEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof BellRungEvent) {
                    final BellRungEvent bellEvent = (BellRungEvent) event;

                    //do something
                    //TODO se tem sirene faz soar sirene else aviso

                }
            }
        });
        bezirk.subscribe(bellRungEvents);
    }




   public static void main(String args[]) {
        new AnalizaCampainha();
       System.out.println(I18N.getString(BELL_ANALYZER_ANNOUNCEMENT));
    }

}
