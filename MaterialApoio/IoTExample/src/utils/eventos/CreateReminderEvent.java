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

    private TipoMensagem tipoMensagem ;



    public CreateReminderEvent(String title, String periodo) throws ParseException {
        this(title,periodo,"00:00:00");
    }

    public CreateReminderEvent(String title, String periodo, String periodicidade) throws ParseException {
       this(title,periodo,periodicidade, TipoMensagem.SMS);

    }
    public CreateReminderEvent(String title, String periodo, String periodicidade, TipoMensagem tm) throws ParseException {
        this.title = title;
        this.periodo = new Date[2];
        String[] temp =periodo.split("-");
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy;HH:mm");
        for (int i = 0; i < 2; i++) {
            this.periodo[i] = sf.parse(temp[i]);
        }
        this.periodicidade = Time.valueOf(periodicidade);
        this.tipoMensagem = tm;
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

    //todo gestor lembretes
    //todo lembrete
    //todo lembrete bd????
}
