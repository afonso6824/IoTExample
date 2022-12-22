package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class SendMessageEvent extends Event {

    //TODO recheck ID
    private static final long serialVersionUID = 1L;
    private final boolean MessageSent;
    public SendMessageEvent(){
        MessageSent = true;
    }

    public String toString() {
        return "A message has been sent to the product owner";
    }
}