package componentes.sensores;


import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import utils.I18N;
import utils.Messages;
import utils.eventos.ButtonPressedEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static utils.Messages.*;

public class CommandZirk {
    private Bezirk bezirk;

    private Boolean online = true;

    public CommandZirk() {
        this.register();
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Command Button Detector Zirk");
        System.err.println(ZIRK_INSTANCE);
    }

    private void register(){
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("APARELHOS");
            property = property + "Comando;";
            prop.setProperty("APARELHOS",property);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void processInput(int in) {
        switch (in) {
            case 8:
                online = false;
                System.out.println(I18N.getString(COMMAND_STOP));
                System.exit(0);
                break;
            case 9:
                System.out.println(I18N.getString(Aparelhos()));
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

    private static Messages Aparelhos(){
        Messages aparelhos = COMMAND_MENU1;
        boolean lampadas =false;
        boolean sirene = false;
        boolean fechadura = false;
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("APARELHOS");
            String[] props = property.split(";");
            for (String s: props) {
                lampadas = lampadas || s.equals("LAMPADA");
                sirene = sirene || s.equals("SIRENE");
                fechadura = fechadura || s.equals("FECHADURA");
            }
            if (lampadas && !sirene && !fechadura){
                aparelhos =  COMMAND_MENU12;
            }
            if (!lampadas && sirene && !fechadura){
                aparelhos =  COMMAND_MENU13;
            }
            if (!lampadas && !sirene && fechadura){
                aparelhos =  COMMAND_MENU14;
            }
            if (lampadas && sirene && !fechadura){
                aparelhos =  COMMAND_MENU123;
            }
            if (lampadas && !sirene && fechadura){
                aparelhos =  COMMAND_MENU124;
            }
            if (!lampadas && sirene && fechadura){
                aparelhos =  COMMAND_MENU134;
            }
            if (lampadas && sirene && fechadura){
                aparelhos =  COMMAND_MENU1234;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return aparelhos;
    }

    private void start() {
        Scanner s = new Scanner(System.in);
        while (online){
            int in = s.nextInt();
            processInput(in);
        }
    }


    public static void main(String args[]) throws InterruptedException {
        CommandZirk commandZirk = new CommandZirk();
        System.out.println(I18N.getString(COMMAND_ANNOUNCEMENT));
        System.out.println(I18N.getString(Aparelhos()));

        commandZirk.start();
    }
}