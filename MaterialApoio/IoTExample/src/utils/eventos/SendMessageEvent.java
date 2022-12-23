package utils.eventos;

import com.bezirk.middleware.messages.Event;
import core.TipoMensagem;

public class SendMessageEvent extends Event {

    private final String Message;



    public SendMessageEvent(String msg){
        this.Message=msg;

    }

    public String getMessage() {
        return Message;
    }



    public String toString() {
        return "A message has been sent to the product owner";
    }
}