package core;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import componentes.analizadores.AnalizaPorta;
import utils.eventos.*;

import java.util.Scanner;

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
                    ext.sendMessage(sendMessageEvent.getMessage(),sendMessageEvent.getTm());
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
    private static void processInput(int in) {
        switch (in) {
            case 9:
                printMenu();
                break;
            case 1:
                Scanner s = new Scanner(System.in);
                System.err.println("Introduza os dados:");
                String msg = s.nextLine();
                //todo filtrar a mensagem
                //CreateReminderEvent createReminderEvent = new CreateReminderEvent();
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
            case 8:
                online = false;
                System.err.println("Sensor Stopped");
                System.exit(0);
                break;
        }
    }

    private static void printMenu() {
        System.err.println("+***************************************************************************************+");
        System.err.println("* This is a door bell detector Mock that uses de input values to simulate a real input. *");
        System.err.println("+***************************************************************************************+");
        System.err.println("");
        System.err.println("1 - Criar Lembrete");
        System.err.println("2 - apagar Lembrete");
        System.err.println("8 - Stop sensor");
        System.err.println("9 - Help");
    }

    public static void main(String[] args) {
        new Analyzer();
        System.err.println("This product has an Analiza ");

        Scanner s = new Scanner(System.in);
        printMenu();
        while (online){
            int in = s.nextInt();
            processInput(in);
        }
    }

}
