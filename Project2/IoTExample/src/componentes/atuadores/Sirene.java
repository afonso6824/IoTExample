package componentes.atuadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import utils.I18N;
import utils.eventos.RingSirenEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static utils.Messages.*;

public class Sirene {

    private final SireneController sireneController;

    public Sirene() {
        this.register();
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

    }
    private void register(){
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("APARELHOS");
            property = property + "Sirene;";
            prop.setProperty("APARELHOS",property);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        public static void main(String[] args) {
            Sirene sirene = new Sirene();
            System.out.println(I18N.getString(SIRENE_ANNOUNCEMENT));
    }
}
