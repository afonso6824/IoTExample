package core.events;

import com.bezirk.middleware.messages.Event;

public class BellPressedEvent extends Event {
    private static final long serialVersionUID = 1L;
    private final boolean bellPressed;
    public BellPressedEvent(){
        bellPressed =true;
    }
    public String toString() {
        return "The bell was pressed";
    }
}
