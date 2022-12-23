package componentes.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import utils.eventos.BellRungEvent;
import utils.eventos.OpenDoorEvent;
import utils.I18N;

import java.util.Scanner;

import static utils.Messages.*;

//VISTO
public class BellDetectorZirk {
    private Bezirk bezirk;

    private Boolean online = true;


    public BellDetectorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Door Bell Detector Zirk");
        System.err.println(ZIRK_INSTANCE);
    }

    private void processInput(int in) {
        switch (in) {
            case 8:
                online = false;
                System.out.println(I18N.getString(SENSOR_STOP));
                System.exit(0);
                break;
            case 9:
                System.out.println(I18N.getString(BELL_DETECTOR_MENU));
                break;
            case 1:
                BellRungEvent bellRungEvent = new BellRungEvent();
                bezirk.sendEvent(bellRungEvent);
                System.out.println(I18N.getString(BELL_RING));

                try {
                    this.wait(5000);
                } catch (InterruptedException e) {
                    System.err.println(I18N.getString(BELL_ERROR));
                }
                System.out.println(I18N.getString(BELL_STOP));
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

/*    private static void printMenu() {
        System.err.println("+***************************************************************************************+");
        System.err.println("* This is a door bell detector Mock that uses de input values to simulate a real input. *");
        System.err.println("+***************************************************************************************+");
        System.err.println("");
        System.err.println("1 - Ring Bell(last 5 sec)");
        System.err.println("8 - Stop sensor");
        System.err.println("9 - Help");
    }*/
    public static void main(String args[]) throws InterruptedException {
        BellDetectorZirk bellDetectorZirk = new BellDetectorZirk();
        System.out.println(I18N.getString(BELL_DETECTOR_ANNOUNCEMENT));


        //printMenu();
        System.out.println(I18N.getString(BELL_DETECTOR_MENU));

        bellDetectorZirk.start();
    }
}