package core;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Lembrete {
    private String title;
    private Date[] periodo;
    private Time periodicidade;
    private boolean active;

    public  Lembrete(String title, Date[] periodo){
        this(title, periodo,Time.valueOf("00:00:00") );
    }

    public  Lembrete(String title, Date[] periodo, Time periodicidade){
        this.title = title;
        this.periodo = periodo;
        this.periodicidade = periodicidade;

        Date current = new Date();
        if (current.after(periodo[0]) && current.before(periodo[1])){
            this.active=true;
        }else {
            this.active=false;
        }
    }

    public String getTitle() {
        return title;
    }

}
