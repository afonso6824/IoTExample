package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class RingSirenEvent extends Event {

    //TODO recheck ID
    private static final long serialVersionUID = 1L;
    private final boolean SirenRung;
    public RingSirenEvent(){
        SirenRung = true;
    }

    public String toString() {
        return "The siren is ringing";
    }
}
