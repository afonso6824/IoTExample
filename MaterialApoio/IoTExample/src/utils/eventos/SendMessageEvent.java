package utils.eventos;

import com.bezirk.middleware.messages.Event;
import core.TipoMensagem;

public class SendMessageEvent extends Event {

    private final String Message;
    private final TipoMensagem tm;

    public SendMessageEvent(String msg){
        this(msg,TipoMensagem.SMS);
    }

    public SendMessageEvent(String msg, TipoMensagem tm){
        this.Message=msg;
        this.tm = tm;
    }

    public String getMessage() {
        return Message;
    }

    public TipoMensagem getTm() {
        return tm;
    }

    public String toString() {
        return "A message has been sent to the product owner";
    }
}