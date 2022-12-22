package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class ButtonPressedEvent extends Event {

    //TODO recheck ID
    private static final long serialVersionUID = 1L;
    private final boolean buttonPressed;
    public ButtonPressedEvent(){
        buttonPressed = true;
    }

    public String toString() {
        return "The command button was pressed";
    }
}
