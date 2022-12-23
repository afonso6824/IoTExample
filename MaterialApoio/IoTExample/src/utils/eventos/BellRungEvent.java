package utils.eventos;

import com.bezirk.middleware.messages.Event;

//VISTO
public class BellRungEvent extends Event {


    private static final long serialVersionUID = 1L;
    private final boolean bellRung;
    public BellRungEvent(){
        bellRung = true;
    }

    public String toString() {
        return "The bell is ringing";
    }
}