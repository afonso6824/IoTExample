package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class DoorUnlockedEvent extends Event {

    private final boolean DoorUnlocked;
    public DoorUnlockedEvent(){
        DoorUnlocked = true;
    }

    public String toString() {
        return "The Door has been unlocked";
    }
}