package components.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import utils.eventos.OpenDoorEvent;

import java.util.Scanner;

import utils.I18N;
import static utils.Messages.DEVICE_RUNNING;

public class BellDetectorZirk {
    private Bezirk bezirk;

    private Boolean online = true;

    //TODO ver parte dos aspects na lingua, ou seja como por as coisas a mandarem mensagens no idioma escolhido
    public BellDetectorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Door Bell Detector Zirk");
        System.err.println("Got Bezirk instance");
    }

    private void processInput(int in) {
        switch (in) {
            case 8:
                online = false;
                System.err.println("Sensor Stopped");
                System.exit(0);
                break;
            case 9:
                printMenu();
                break;
            case 1:
                OpenDoorEvent openDoorEvent = new OpenDoorEvent();
                bezirk.sendEvent(openDoorEvent);
                System.err.println("Published bell rung update: " + openDoorEvent.toString());

                try {//TODO change to different spot??
                    this.wait(5000);
                } catch (InterruptedException e) {
                    System.err.println("Bell detector couldn't wait 5 sec before resetting");
                }
                System.err.println("Bell ringing stopped");
                break;
        }
    }

    private void start() {
        Scanner s = new Scanner(System.in);
        while (online){
            int in = s.nextInt();
            processInput(in);
        }
    }

    private static void printMenu() {
        System.err.println("+***************************************************************************************+");
        System.err.println("* This is a door bell detector Mock that uses de input values to simulate a real input. *");
        System.err.println("+***************************************************************************************+");
        System.err.println("");
        System.err.println("1 - Ring Bell(last 5 sec)");
        System.err.println("8 - Stop sensor");
        System.err.println("9 - Help");
    }
    public static void main(String args[]) throws InterruptedException {
        BellDetectorZirk bellDetectorZirk = new BellDetectorZirk();
        System.err.println("This product has a Door Bell detector");
        //TODO fix null pointer exception
        //System.err.println(I18N.getString(DEVICE_RUNNING, "Door Bell Detector"));
        printMenu();

        bellDetectorZirk.start();
    }
}