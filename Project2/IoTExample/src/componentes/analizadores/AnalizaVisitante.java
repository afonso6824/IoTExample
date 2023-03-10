package componentes.analizadores;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import utils.I18N;
import utils.Messages;
import utils.eventos.OpenDoorEvent;
import utils.eventos.BellRungEvent;
import utils.eventos.SendMessageEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static utils.Messages.VISITOR_ANALYZER_ANNOUNCEMENT;
import static utils.Messages.ZIRK_INSTANCE;

public class AnalizaVisitante {
    public AnalizaVisitante() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Analiza Visitante Zirk");
        System.err.println(ZIRK_INSTANCE);

        final EventSet openDoorEvents = new EventSet(OpenDoorEvent.class,BellRungEvent.class);


        openDoorEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof BellRungEvent) {
                    final BellRungEvent bellEvent = (BellRungEvent) event;

                    openDoorEvents.setEventReceiver(new EventSet.EventReceiver() {
                        @Override
                        public void receiveEvent(Event event, ZirkEndPoint sender) {
                            if (event instanceof OpenDoorEvent) {
                                final OpenDoorEvent doorEvent = (OpenDoorEvent) event;
                                if (new Date().after(getPeriodo()[0]) && new Date().before(getPeriodo()[1]) ){
                                    SendMessageEvent sendMessageEvent = new SendMessageEvent("Visitante detetado");
                                    bezirk.sendEvent(sendMessageEvent);
                                }
                            }
                        }});
                                                    }

                }
            });
        bezirk.subscribe(openDoorEvents);
    }

    private Date[] getPeriodo(){
        Date[] data = new Date[2];
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("PERIODO_DIA");
            String[] props = property.split(";");
            data[0] = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(props[0]);
            data[1] = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(props[1]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return data;
    }


    public static void main(String args[]) {
        new AnalizaVisitante();
        System.out.println(I18N.getString(VISITOR_ANALYZER_ANNOUNCEMENT));
    }

}
