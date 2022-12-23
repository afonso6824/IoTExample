package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class RefreshTimeInterval extends Event {

    private final boolean RefreshTI;
    public RefreshTimeInterval(){
        RefreshTI = true;
    }

    public String toString() {
        return "The Time Interval has been changed";
    }
}
