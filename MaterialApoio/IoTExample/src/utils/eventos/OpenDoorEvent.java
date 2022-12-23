package utils.eventos;

import com.bezirk.middleware.messages.Event;

import java.util.Date;

public class OpenDoorEvent extends Event {
    private Date sendTime;
    private final boolean doorOpen;
    public OpenDoorEvent(){
        doorOpen = true;
        sendTime= new Date();
    }

    public String toString() {
        return "The door is open";
    }
}
