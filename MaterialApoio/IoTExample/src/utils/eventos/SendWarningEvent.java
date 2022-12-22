package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class SendWarningEvent extends Event {

    //TODO recheck ID
    private static final long serialVersionUID = 1L;
    private final boolean WarningSent;
    public SendWarningEvent(){
        WarningSent = true;
    }

    public String toString() {
        return "A warning has been sent to the House PC";
    }
}