package componentes.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import utils.I18N;
import utils.Messages;
import utils.eventos.CloseDoorEvent;
import utils.eventos.OpenDoorEvent;

import java.sql.Time;
import java.util.Scanner;

import static utils.Messages.*;
//VISTO
public class DoorSensorZirk {
    private Bezirk bezirk;

    private boolean doorOpen = false;
    private Boolean online = true;

    public DoorSensorZirk() {

        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Door Movement Sensor Zirk");
        System.err.println(ZIRK_INSTANCE);
    }

    private void start() {
        Scanner s = new Scanner(System.in);
        while (online) {
            int in = s.nextInt();
            processInput(in);
        }
    }


    private void processInput(int in) {
        switch (in) {
            case 8:
                doorOpen = false;
                online = false;
                System.out.println(I18N.getString(SENSOR_STOP));
                System.exit(0);
                break;
            case 9:
                System.out.println(I18N.getString(DOOR_SENSOR_MENU));
                break;
            case 1:
                doorOpen = true;
                OpenDoorEvent openDoorEvent = new OpenDoorEvent();
                bezirk.sendEvent(openDoorEvent);
                System.out.println(I18N.getString(DOOR_OPEN));
                break;
            case 2:
                doorOpen = false;
                CloseDoorEvent closeDoorEvent = new CloseDoorEvent();
                bezirk.sendEvent(closeDoorEvent);
                System.out.println(I18N.getString(DOOR_CLOSED));
                break;
        }
    }

   /* private static void printMenu() {
        System.err.println("+************************************************************************************+");
        System.err.println("* This is a Mock sensor that uses de input values to simulate a real movement input. *");
        System.err.println("+************************************************************************************+");
        System.err.println("");
        System.err.println("1 - Open Door");
        System.err.println("2 - Close Door");
        System.err.println("8 - Stop sensor");
        System.err.println("9 - Help");
    }*/

    public static void main(String args[]) throws InterruptedException {
        DoorSensorZirk doorSensorZirk = new DoorSensorZirk();
        System.out.println(I18N.getString(DOOR_SENSOR_ANNOUNCEMENT));

        System.out.println(I18N.getString(DOOR_SENSOR_MENU));
        //printMenu();

        doorSensorZirk.start();
    }
}