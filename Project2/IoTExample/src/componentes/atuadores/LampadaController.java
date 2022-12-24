package componentes.atuadores;

import utils.I18N;

import static utils.Messages.LAMP_OFF;

public class LampadaController {
    private  boolean status;

    public LampadaController(){
        this.status = false;
    }
    public void turnOn(){
        this.status = true;
    }
    public void turnOff(){
        this.status = false;
        System.out.println(I18N.getString(LAMP_OFF));
    }
    public boolean isOn(){
        return this.status;
    }



}
