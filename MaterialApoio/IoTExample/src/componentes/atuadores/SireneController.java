package componentes.atuadores;

import utils.I18N;

import static utils.Messages.*;

public class SireneController {
    private Song song;
    private boolean hasLEDLights;

    public SireneController(){
        this(Song.SONG1);
    }
    public SireneController(Song song){
        this.song = song;
        //todo ver se tem ledlights
    }
    public void ring() {
        System.out.println("Ring Ring");
        if (hasLEDLights)
            System.out.println(I18N.getString(SIRENE_LED));

    }

    public void stop() {
        System.out.println(I18N.getString(SIRENE_STOP));
        if (hasLEDLights)
            System.out.println(I18N.getString(SIRENE_LED_STOP));
    }
}
