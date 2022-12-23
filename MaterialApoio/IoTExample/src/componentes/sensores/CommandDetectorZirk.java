package componentes.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import utils.I18N;
import utils.eventos.ButtonPressedEvent;

import java.util.Scanner;

import static utils.Messages.*;

public class CommandDetectorZirk {
    private Bezirk bezirk;

    private Boolean online = true;

    //TODO ver se nao deve ser so comando
    public CommandDetectorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Command Button Detector Zirk");
        System.err.println(ZIRK_INSTANCE);
    }

    private void processInput(int in) {
        switch (in) {
            case 8:
                online = false;
                System.out.println(I18N.getString(COMMAND_STOP));
                System.exit(0);
                break;
            case 9:
                // todo printMenu();
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                ButtonPressedEvent buttonPressedEvent = new ButtonPressedEvent(in);
                bezirk.sendEvent(buttonPressedEvent);
                System.err.println(I18N.getString(COMMAND_PRESSED, String.valueOf(in)));
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

    /*
    private static void printMenu() {
        System.err.println("+************************************************************************************+");
        System.err.println("* This is a Mock *");
        System.err.println("+************************************************************************************+");
        System.err.println("");
        System.err.println("1 - Press Button SOS");
        System.err.println("2 - Press Button Lights OFF");
        System.err.println("3 - Press Button Siren ON");
        System.err.println("4 - Press Button Open Door");
        System.err.println("8 - Stop sensor");
        System.err.println("9 - Help");
    }*/
    public static void main(String args[]) throws InterruptedException {
        CommandDetectorZirk commandDetectorZirk = new CommandDetectorZirk();
        System.out.println(I18N.getString(COMMAND_ANNOUNCEMENT));
        //TODO fazer prints com if
        //if ()
        //System.err.println(I18N.getString(DEVICE_RUNNING, "Command Button Detector"));
       // printMenu();

        commandDetectorZirk.start();
    }
}