package components.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import utils.eventos.OpenDoorEvent;

import java.util.Scanner;

import utils.I18N;
import static utils.Messages.DEVICE_RUNNING;

public class DoorSensorZirk {
    private Bezirk bezirk;
    private boolean doorOpen = false;
    private Boolean online = true;

    //TODO ver parte dos aspects na lingua, ou seja como por as coisas a mandarem mensagens no idioma escolhido
    public DoorSensorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Door Movement Sensor Zirk");
        System.err.println("Got Bezirk instance");
    }

    private void start() {
        Scanner s = new Scanner(System.in);
        while (online) {
            int in = s.nextInt();
            processInput(in);
        }
    }

    //TODO ver se vale a pena mandar evento periodico
    private void processInput(int in) {
        switch (in) {
            case 8:
                doorOpen = false;
                online = false;
                System.err.println("Sensor Stopped");
                System.exit(0);
                break;
            case 9:
                printMenu();
                break;
            case 1:
                doorOpen = true;
                OpenDoorEvent openDoorEvent = new OpenDoorEvent();
                bezirk.sendEvent(openDoorEvent);
                System.err.println("Published open door update: " + openDoorEvent.toString());
                break;
            case 2:
                doorOpen = false;
                System.err.println("Door closed");
                break;
        }
    }

    private static void printMenu() {
        System.err.println("+************************************************************************************+");
        System.err.println("* This is a Mock sensor that uses de input values to simulate a real movement input. *");
        System.err.println("+************************************************************************************+");
        System.err.println("");
        System.err.println("1 - Open Door");
        System.err.println("2 - Close Door");
        System.err.println("8 - Stop sensor");
        System.err.println("9 - Help");
    }

    public static void main(String args[]) throws InterruptedException {
        DoorSensorZirk doorSensorZirk = new DoorSensorZirk();
        System.err.println("This product has a Door Movement Sensor");
        //TODO fix null pointer exception
        //System.err.println(I18N.getString(DEVICE_RUNNING, "Door Movement Sensor"));
        printMenu();

        doorSensorZirk.start();
    }
}