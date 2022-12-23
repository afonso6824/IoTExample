package core;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import utils.I18N;
import utils.eventos.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import static utils.Messages.ANALYZER_MENU;
import static utils.Messages.BELL_DETECTOR_MENU;

public class Analyzer {
    private static Bezirk bezirk;
    private GestorLembretes gl;
    private GestorExterior ext;
    private GestorAvisos avs;
    private static Boolean online = true;
    public Analyzer() {
        gl = new GestorLembretes();
        ext = new GestorExterior();
        avs = new GestorAvisos();
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Analiza Visitante Zirk");
        System.err.println("Got Bezirk instance");


        final EventSet lembretesEvents = new EventSet(CreateReminderEvent.class, DeleteReminderEvent.class, SendWarningEvent.class, SendMessageEvent.class);

        lembretesEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof CreateReminderEvent) {
                    final CreateReminderEvent createReminderEvent = (CreateReminderEvent) event;
                    System.err.println("\nReceived air quality update: " + createReminderEvent.toString());
                    //do something
                    Lembrete l;
                    if (createReminderEvent.getPeriodicidade() != null)
                        l = new Lembrete(createReminderEvent.getTitle(), createReminderEvent.getPeriodo(), createReminderEvent.getPeriodicidade());
                    else
                        l = new Lembrete(createReminderEvent.getTitle(), createReminderEvent.getPeriodo());
                    gl.addLembrete(l);
                }

                if (event instanceof DeleteReminderEvent) {
                    final  DeleteReminderEvent deleteReminderEvent = (DeleteReminderEvent) event;
                    System.err.println("\nReceived air quality update: " + deleteReminderEvent.toString());
                    gl.removeLembrete(deleteReminderEvent.getTitle());
                }
                if (event instanceof SendMessageEvent) {
                    final SendMessageEvent sendMessageEvent = (SendMessageEvent) event;
                    System.err.println("\nReceived air quality update: " + sendMessageEvent.toString());
                    ext.sendMessage(sendMessageEvent.getMessage());
                }

                if (event instanceof SendWarningEvent) {
                    final  SendWarningEvent sendWarningEvent = (SendWarningEvent) event;
                    System.err.println("\nReceived air quality update: " + sendWarningEvent.toString());
                    avs.sendAviso(sendWarningEvent.getMsg());
                }
            }
        });
        bezirk.subscribe(lembretesEvents);
    }
    private static void processInput(int in) throws ParseException {
        switch (in) {
            case 9:
                System.out.println(I18N.getString(ANALYZER_MENU));
                break;
            case 1:
                Scanner s = new Scanner(System.in);
                System.out.println("Introduza o titulo:");
                String title = s.nextLine();
                System.err.println("Introduza 1ª data:");
                String data1 = s.nextLine();
                System.err.println("Introduza 2ª data:");
                String data2 = s.nextLine();
                Date[] time = new Date[2];
                time[0 ]= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(data1);
                time[1 ]= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(data2);
                System.out.println("Introduza o periodicidade:");
                String period = s.nextLine();


                CreateReminderEvent createReminderEvent = new CreateReminderEvent(title,time,period);
                // bezirk.sendEvent(createReminderEvent);
                //System.err.println();
                break;
            case 2:
                Scanner s2 = new Scanner(System.in);
                System.err.println("Introduza titulo do lembrete a remover:");
                String msg2 = s2.nextLine();
                DeleteReminderEvent deleteReminderEvent =  new DeleteReminderEvent(msg2);
                bezirk.sendEvent(deleteReminderEvent);
                System.err.println("");
                break;
            case 3:
                Scanner s3 = new Scanner(System.in);
                System.err.println("Introduza 1ª data:");
                String data01 = s3.nextLine();
                System.err.println("Introduza 2ª data:");
                String data02 = s3.nextLine();
                Date[] time1 = new Date[2];
                time1[0 ]= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(data01);
                time1[1 ]= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(data02);

                changePeriodDay(time1);
                break;
            case 8:
                online = false;
                System.err.println("Sensor Stopped");
                System.exit(0);
                break;
        }
    }
    private static void changePeriodDay(Date[] day){
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            prop.setProperty("PERIODO_DIA",String.valueOf(day[0] + ";" + day[1]));;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ParseException {
        new Analyzer();
        System.err.println("This product has an Analiza ");

        Scanner s = new Scanner(System.in);
        System.out.println(I18N.getString(ANALYZER_MENU));

        while (online){
            int in = s.nextInt();
            processInput(in);
        }
    }

}
