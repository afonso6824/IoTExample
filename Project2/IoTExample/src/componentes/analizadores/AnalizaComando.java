package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.I18N;
import utils.eventos.*;

import static utils.Messages.*;

public class AnalizaComando {

    public AnalizaComando() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Comando Zirk");
        System.err.println(ZIRK_INSTANCE);
        final EventSet buttonPressedEvents = new EventSet(ButtonPressedEvent.class);

        buttonPressedEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof ButtonPressedEvent) {
                    final ButtonPressedEvent buttonEvent = (ButtonPressedEvent) event;

                    //do something
                    int button = buttonEvent.getButtonPressed();
                    switch (button){
                        case 1:
                            SendMessageEvent sendMessageEvent = new SendMessageEvent("Pedido de ajuda enviado");
                            SendWarningEvent sendWarningEvent = new SendWarningEvent("Pedido de ajuda");
                            bezirk.sendEvent(sendMessageEvent);
                            bezirk.sendEvent(sendWarningEvent);
                            break;
                        case 2:
                            LightsOFFEvent lightsOFFEvent= new LightsOFFEvent();
                            bezirk.sendEvent(lightsOFFEvent);
                            break;
                        case 3:
                            RingSirenEvent ringSirenEvent = new RingSirenEvent();
                            bezirk.sendEvent(ringSirenEvent);
                            break;
                        case 4:
                            DoorUnlockedEvent doorUnlockedEvent = new DoorUnlockedEvent();
                            bezirk.sendEvent(doorUnlockedEvent);
                            break;
                    }

                }
            }
        });
        bezirk.subscribe(buttonPressedEvents);
    }




    public static void main(String args[]) {
        new AnalizaComando();
        System.out.println(I18N.getString(COMMAND_ANALYZER_ANNOUNCEMENT));
    }

}
