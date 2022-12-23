package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class LightsOFFEvent extends Event {

    private final boolean LightOFF;
    public LightsOFFEvent(){
        LightOFF = true;
    }

    public String toString() {
        return "The Light has been turn off";
    }
}