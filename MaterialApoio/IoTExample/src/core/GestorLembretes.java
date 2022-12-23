package core;

import java.util.ArrayList;
import java.util.List;

public class GestorLembretes {
    List<Lembrete> lembretes;

    public GestorLembretes(){
        this.lembretes = new ArrayList<Lembrete>();
    }

    public void addLembrete(Lembrete lembrete){
        this.lembretes.add(lembrete);
    }

    public void removeLembrete(String title){
        for (Lembrete curInstance: this.lembretes) {
            if ( curInstance.getTitle().equals(title)){
                lembretes.remove(curInstance);
            }
        }
    }


}
