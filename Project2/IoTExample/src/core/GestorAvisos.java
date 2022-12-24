package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GestorAvisos {

    private boolean problemasDeAudiçao;
    public GestorAvisos(){
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            problemasDeAudiçao = Boolean.parseBoolean(prop.getProperty("PROBLEMAS_AUDITIVOS"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendAviso(String msg){
        System.out.println("ecrâ: " + msg);
        if (problemasDeAudiçao){
            System.out.println("Sinais de luzes ");
        }
        else {
            System.out.println("Voz Sintetizada: " + msg);
        }
    }
}
