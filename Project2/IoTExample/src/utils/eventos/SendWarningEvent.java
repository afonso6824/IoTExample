package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class SendWarningEvent extends Event {
    private String msg;

    public SendWarningEvent(String msg){
       this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public String toString() {
        return "A warning has been sent to the House PC";
    }
}