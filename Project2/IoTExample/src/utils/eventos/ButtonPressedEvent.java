package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class ButtonPressedEvent extends Event {

    private final int buttonPressed;
    public ButtonPressedEvent(int number){
        buttonPressed = number;
    }

    public int getButtonPressed() {
        return buttonPressed;
    }

    public String toString() {
        String toPrint;
        switch (this.buttonPressed){
            case 1:
                toPrint = "SOS button pressed";
                break;
            case 2:
                toPrint = "Lights off button pressed";
                break;
            case 3:
                toPrint = "sirene on button pressed";
                break;
            case 4:
                toPrint = "door open button pressed";
                break;
            default:
                toPrint = "Error in command buttons";
                break;
        }

        return toPrint;
    }
}
