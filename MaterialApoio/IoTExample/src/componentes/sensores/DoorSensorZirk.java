package componentes.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import utils.I18N;
import utils.Messages;
import utils.eventos.CloseDoorEvent;
import utils.eventos.OpenDoorEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.Properties;
import java.util.Scanner;

import static utils.Messages.*;

public class DoorSensorZirk {
    private Bezirk bezirk;

    private boolean doorOpen = false;
    private Boolean online = true;

    public DoorSensorZirk() {
        this.register();
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Door Movement Sensor Zirk");
        System.err.println(ZIRK_INSTANCE);
    }

    private void register(){
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("APARELHOS");
            property = property + "Sensor;";
            prop.setProperty("APARELHOS",property);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                System.out.println(I18N.getString(DOOR_OPEN));


                try {
                    wait(getDoorTime());
                    if (doorOpen){
                        OpenDoorEvent openDoorEvent = new OpenDoorEvent();
                        bezirk.sendEvent(openDoorEvent);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                break;
            case 2:
                doorOpen = false;
                CloseDoorEvent closeDoorEvent = new CloseDoorEvent();
                bezirk.sendEvent(closeDoorEvent);
                System.out.println(I18N.getString(DOOR_CLOSED));
                break;
            case 3:
                Scanner s = new Scanner(System.in);
                System.err.println("Introduza tempo:");
                int time = s.nextInt();
                changeTime(time);
                break;
        }
    }

    private void changeTime(int time){
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            prop.setProperty("TEMPO_PORTA_ABERTA",String.valueOf(time));;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private int getDoorTime(){
        int time = 0;
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("TEMPO_PORTA_ABERTA");
            time = Integer.parseInt(property) * 1000;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return time;
    }


    public static void main(String args[]) throws InterruptedException {
        DoorSensorZirk doorSensorZirk = new DoorSensorZirk();
        System.out.println(I18N.getString(DOOR_SENSOR_ANNOUNCEMENT));

        System.out.println(I18N.getString(DOOR_SENSOR_MENU));


        doorSensorZirk.start();
    }
}