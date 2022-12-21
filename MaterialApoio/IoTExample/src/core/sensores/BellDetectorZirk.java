package core.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import core.events.BellPressedEvent;
import core.events.OpenDoorEvent;
import i18n.I18N;

import java.util.Scanner;

import static i18n.Messages.DEVICE_RUNNING;

public class BellDetectorZirk {
    private Bezirk bezirk;

    //TODO ver lingua
    public BellDetectorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Bell Detector Zirk");
        System.err.println("Got Bezirk instance");
    }
    private void start() {
        Scanner s = new Scanner(System.in);
        while (true){
            int in = s.nextInt();
            processInput(in);
        }
    }

    private void processInput(int in) {
        switch (in) {
            case 9:
                printMenu();
                break;
            case 0:
                BellPressedEvent bellPressedEvent = new BellPressedEvent();
                bezirk.sendEvent(bellPressedEvent);
                System.err.println("Published bell update: " + bellPressedEvent.toString());
                break;



        }
    }
    public static void main(String args[]) throws InterruptedException {
        BellDetectorZirk bellDetectorZirk = new BellDetectorZirk();
        System.err.println("This product has an Bell Detector");
        System.err.println(I18N.getString(DEVICE_RUNNING, "Bell Detector"));
        printMenu();

        bellDetectorZirk.start();
    }

    private static void printMenu() {
        System.err.println("+***************************************************************************************+");
        System.err.println("* This is a door bell detector Mock that uses de input values to simulate a real input. *");
        System.err.println("+***************************************************************************************+");
        System.err.println();
        System.err.println("0 - Press Door Bell");
        System.err.println("9 - Help");
    }

}