package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class RingSirenEvent extends Event {

    private final boolean SirenRung;
    public RingSirenEvent(){
        SirenRung = true;
    }

    public String toString() {
        return "The siren is ringing";
    }
}
