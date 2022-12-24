package componentes.atuadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import utils.I18N;
import utils.Messages;
import utils.eventos.LightsOFFEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static utils.Messages.LAMP_OFF;
import static utils.Messages.LAMP_ANNOUNCEMENT;
import static utils.Messages.*;

public class Lampada {

    private final LampadaController lampadaController;

    public Lampada(){
        this.register();
        BezirkMiddleware.initialize();
        Bezirk bezirk = BezirkMiddleware.registerZirk("Lock Zirk");
        System.err.println(ZIRK_INSTANCE);
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

    private void register(){
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("APARELHOS");
            property = property + "Lampada;";
            prop.setProperty("APARELHOS",property);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return lampadaController.toString();
    }

    public static void main(String[] args) {
        Lampada lampada = new Lampada();
        System.out.println(I18N.getString(LAMP_ANNOUNCEMENT));
    }
}
