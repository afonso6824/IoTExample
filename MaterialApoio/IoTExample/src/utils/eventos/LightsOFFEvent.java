package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class LightsOFFEvent extends Event {

    //TODO recheck ID
    private static final long serialVersionUID = 1L;
    private final boolean LightOFF;
    public LightsOFFEvent(){
        LightOFF = true;
    }

    public String toString() {
        return "The Light has been turn off";
    }
}