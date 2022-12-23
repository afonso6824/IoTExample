package componentes.atuadores;

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
            System.out.println("LED Lights activated");

    }

}
