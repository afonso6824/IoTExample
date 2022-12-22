package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class RefreshTimeOfDay extends Event {

    //TODO recheck ID
    private static final long serialVersionUID = 1L;
    private final boolean RefreshTD;
    public RefreshTimeOfDay(){
        RefreshTD = true;
    }

    public String toString() {
        return "The Time of Day has been changed";
    }
}
