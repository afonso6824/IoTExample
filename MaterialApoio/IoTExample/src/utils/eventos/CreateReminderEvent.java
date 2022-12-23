package utils.eventos;

import com.bezirk.middleware.messages.Event;
import core.TipoMensagem;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


public class CreateReminderEvent extends Event {
    private String title;
    private Date[] periodo;
    private Time periodicidade = null;



    public CreateReminderEvent(String title, Date[] periodo) throws ParseException {
        this(title,periodo,"00:00:00");
    }


    public CreateReminderEvent(String title, Date[] periodo, String periodicidade) throws ParseException {
        this.title = title;
        this.periodo = periodo;
        if (periodicidade != "")
            this.periodicidade = Time.valueOf(periodicidade);

    }

    public String getTitle() {
        return title;
    }

    public Date[] getPeriodo() {
        return periodo;
    }

    public Time getPeriodicidade() {
        return periodicidade;
    }

    @Override
    public String toString() {
        return "The lembrete event";
    }

}
