package componentes.atuadores;

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
    }
    public boolean isOn(){
        return this.status;
    }

    @Override
    public String toString() {
        if (this.isOn()){
            return "Light is on";
        }else {
            return "Light is off";
        }
    }

}
