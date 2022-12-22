package components.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import utils.I18N;
import utils.eventos.ButtonPressedEvent;

import java.util.Scanner;

import static utils.Messages.DEVICE_RUNNING;

public class CommandDetectorZirk {
    private Bezirk bezirk;

    private Boolean button1Pressed = false;
    private Boolean button2Pressed = false;
    private Boolean button3Pressed = false;
    private Boolean button4Pressed = false;
    private Boolean online = true;

    //TODO ver parte dos aspects na lingua, ou seja como por as coisas a mandarem mensagens no idioma escolhido
    public CommandDetectorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Door Bell Detector Zirk");
        System.err.println("Got Bezirk instance");
    }

    private void start() {
        Scanner s = new Scanner(System.in);
        while (online){
            int in = s.nextInt();
            processInput(in);
        }
    }

    //TODO ver se vale a pena mandar evento periodico && extrair criacao de eventos para nova funcao
    private void processInput(int in) {
        switch (in) {
            case 8:
                button1Pressed = false;
                button2Pressed = false;
                button3Pressed = false;
                button4Pressed = false;
                online = false;
                System.err.println("Sensor Stopped");
                System.exit(0);
                break;
            case 9:
                printMenu();
                break;
            case 0:
                button1Pressed = true;
                ButtonPressedEvent buttonPressedEvent = new ButtonPressedEvent();
                bezirk.sendEvent(buttonPressedEvent);
                System.err.println("Published open door update: " + buttonPressedEvent.toString());
                try {
                    this.wait(500);
                } catch (InterruptedException e) {
                    System.err.println("Bell detector couldn't wait 0.5 sec before resetting");
                }
                button1Pressed = false;
                break;
            case 1:
                button2Pressed = true;
                ButtonPressedEvent buttonPressedEvent2 = new ButtonPressedEvent();
                bezirk.sendEvent(buttonPressedEvent2);
                System.err.println("Published open door update: " + buttonPressedEvent2.toString());
                try {
                    this.wait(500);
                } catch (InterruptedException e) {
                    System.err.println("Bell detector couldn't wait 0.5 sec before resetting");
                }
                button2Pressed = false;
                break;
            case 2:
                button3Pressed = true;
                ButtonPressedEvent buttonPressedEvent3 = new ButtonPressedEvent();
                bezirk.sendEvent(buttonPressedEvent3);
                System.err.println("Published open door update: " + buttonPressedEvent3.toString());
                try {
                    this.wait(500);
                } catch (InterruptedException e) {
                    System.err.println("Bell detector couldn't wait 0.5 sec before resetting");
                }
                button3Pressed = false;
                break;
            case 3:
                button4Pressed = true;
                ButtonPressedEvent buttonPressedEvent4 = new ButtonPressedEvent();
                bezirk.sendEvent(buttonPressedEvent4);
                System.err.println("Published open door update: " + buttonPressedEvent4.toString());
                try {
                    this.wait(500);
                } catch (InterruptedException e) {
                    System.err.println("Bell detector couldn't wait 0.5 sec before resetting");
                }
                button4Pressed = false;
                break;
        }
    }

    private static void printMenu() {
        System.err.println("+************************************************************************************+");
        System.err.println("* This is a Mock sensor that uses de input values to simulate a real movement input. *");
        System.err.println("+************************************************************************************+");
        System.err.println("");
        System.err.println("0 - Press Button one");
        System.err.println("1 - Press Button two");
        System.err.println("2 - Press Button three");
        System.err.println("3 - Press Button four");
        System.err.println("8 - Stop sensor");
        System.err.println("9 - Help");
    }
    public static void main(String args[]) throws InterruptedException {
        CommandDetectorZirk commandDetectorZirk = new CommandDetectorZirk();
        System.err.println("This product has a command button detector");
        //TODO fix null pointer exception
        //System.err.println(I18N.getString(DEVICE_RUNNING, "Command Button Detector"));
        printMenu();

        commandDetectorZirk.start();
    }
}