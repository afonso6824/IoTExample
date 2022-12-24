package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class CloseDoorEvent extends Event {
    private static final long serialVersionUID = 1L;
    private final boolean doorClosed;

    public CloseDoorEvent(){
        doorClosed = true;
    }

    public String toString() {
        return "The door is closed";
    }
}
