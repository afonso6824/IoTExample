package core.sensores;



import core.events.OpenDoorEvent;
import i18n.I18N;
import static i18n.Messages.DEVICE_RUNNING;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import java.util.Scanner;

public class DoorSensorZirk{
    private Bezirk bezirk;
    private boolean doorOpen;

    //TODO ver parte dos aspects na lingua, ou seja como por as coisas a mandarem emnsagens no idioma escolhido
    public DoorSensorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Door Movement Sensor Zirk");
        System.err.println("Got Bezirk instance");
    }
    private void start() {
        Scanner s = new Scanner(System.in);
        while (true){
            int in = s.nextInt();
            processInput(in);
        }
    }

    //TODO ver se vale a pena mandar evento periodico
    private void processInput(int in) {
        switch (in) {
            case 9:
                printMenu();
                break;
            case 0:
                doorOpen = true;
                OpenDoorEvent openDoorEvent = new OpenDoorEvent();
                bezirk.sendEvent(openDoorEvent);
                System.err.println("Published open door update: " + openDoorEvent.toString());
                break;
            case 1:
                doorOpen = false;
                System.err.println("Door closed");
                break;


        }
    }



    public static void main(String args[]) throws InterruptedException {
        DoorSensorZirk doorSensorZirk = new DoorSensorZirk();
        System.err.println("This product has an Door Movement Sensor");
        System.err.println(I18N.getString(DEVICE_RUNNING, "Air Quality Sensor"));
        printMenu();

        doorSensorZirk.start();
     }

    private static void printMenu() {
        System.err.println("+************************************************************************************+");
        System.err.println("* This is a Mock sensor that uses de input values to simulate a real movement input. *");
        System.err.println("+************************************************************************************+");
        System.err.println("");
        System.err.println("0 - Open Door");
        System.err.println("1 - Close Door");
        System.err.println("9 - Help");
    }
}