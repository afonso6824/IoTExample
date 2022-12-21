package core.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import core.events.ButtonPressedEvent;
import i18n.I18N;

import java.util.Scanner;

import static i18n.Messages.DEVICE_RUNNING;

public class CommandDetectorZirk {
    private Bezirk bezirk;

    //TODO ver lingua
    //TODO falta fazer sair
    //TODO ver se nao deve ser so comando
    public CommandDetectorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Command Detector Zirk");
        System.err.println("Got Bezirk instance");
    }




    public static void main(String args[]) throws InterruptedException {
        CommandDetectorZirk commandDetectorZirk = new CommandDetectorZirk();
        System.err.println("This product has an Command Detector");
        System.err.println(I18N.getString(DEVICE_RUNNING, "Command Detector"));
        printMenu();

        commandDetectorZirk.start();

     }

    private void start() {
        Scanner s = new Scanner(System.in);
        while (true){
            int in = s.nextInt();
            processInput(in);
        }
    }
    private static void printMenu() {
        System.err.println("+************************************************************************************+");
        System.err.println("* This is a Mock *"); //TODO fazer depois
        System.err.println("+************************************************************************************+");
        System.err.println("");
        System.err.println("1 - SOS");
        System.err.println("2 - turn off lights");
        System.err.println("3 - turn on sirene");
        System.err.println("4 - Open Door");
        System.err.println("9 - Menu");
    }

    private void processInput(int in) {
        switch (in) {
            case 9:
                printMenu();
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                ButtonPressedEvent buttonPressedEvent = new ButtonPressedEvent(in);
                bezirk.sendEvent(buttonPressedEvent);
                System.err.println("Published button pressed update: " + buttonPressedEvent.toString());
                break;

    }


}}