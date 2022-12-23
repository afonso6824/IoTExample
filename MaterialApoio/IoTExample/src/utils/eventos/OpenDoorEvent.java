package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class OpenDoorEvent extends Event {

    private static final long serialVersionUID = 1L;
    private final boolean doorOpen;
    public OpenDoorEvent(){
        doorOpen = true;
    }

    public String toString() {
        return "The door is open";
    }
}
