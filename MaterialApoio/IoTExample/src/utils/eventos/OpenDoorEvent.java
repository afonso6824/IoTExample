package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class OpenDoorEvent extends Event {

    private final boolean doorOpen;
    public OpenDoorEvent(){
        doorOpen = true;
    }

    public String toString() {
        return "The door is open";
    }
}
