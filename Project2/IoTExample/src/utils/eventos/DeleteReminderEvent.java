package utils.eventos;

import com.bezirk.middleware.messages.Event;

public class DeleteReminderEvent extends Event {

    private String title;

    public DeleteReminderEvent(String title){
        this.title=title;
    }
    @Override
    public String toString() {
        return "The delete lembrete event";
    }

    public String getTitle() {
        return title;
    }
}
