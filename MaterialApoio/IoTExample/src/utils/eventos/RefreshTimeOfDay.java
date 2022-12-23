package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class RefreshTimeOfDay extends Event {

    private final boolean RefreshTD;
    public RefreshTimeOfDay(){
        RefreshTD = true;
    }

    public String toString() {
        return "The Time of Day has been changed";
    }
}
