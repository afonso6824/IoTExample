package componentes.atuadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.I18N;
import utils.Messages;
import utils.eventos.DoorUnlockedEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static utils.Messages.LOCK_ANNOUNCEMENT;
import static utils.Messages.ZIRK_INSTANCE;


public class Fechadura {
    private final FechaduraController fechaduraController;

    public Fechadura(){
        this(1234);
    }
    public Fechadura(int code){
        this.register();
        BezirkMiddleware.initialize();
        Bezirk bezirk = BezirkMiddleware.registerZirk("Lock Zirk");
        System.err.println(ZIRK_INSTANCE);
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
    private void register(){
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("APARELHOS");
            property = property + "Fechadura;";
            prop.setProperty("APARELHOS",property);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
