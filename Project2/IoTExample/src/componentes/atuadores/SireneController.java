package componentes.atuadores;

import utils.I18N;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static utils.Messages.*;

public class SireneController {
    private Song song;


    public SireneController(){
        this(Song.SONG1);
    }
    public SireneController(Song song){
        this.song = song;
    }
    public void ring() {
        System.out.println("Ring Ring");
        if (hasLED())
            System.out.println(I18N.getString(SIRENE_LED));

    }

    public void stop() {
        System.out.println(I18N.getString(SIRENE_STOP));
        if (hasLED())
            System.out.println(I18N.getString(SIRENE_LED_STOP));
    }
    private boolean hasLED(){
        boolean hasLED =false;
        String configFilePath = "src/utils/config.properties";
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("APARELHOS");
            String[] props = property.split(";");
            for (String s: props) {
                hasLED = hasLED ||s.equals("LED");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hasLED;
    }
}
