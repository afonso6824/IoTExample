package componentes.atuadores;

public class Door {
    private boolean open;

    public Door(){
        this.open=false;
    }

    public void openDoor(){
        this.open=true;
    }

    public void closeDoor() {
        this.open=false;
    }
    public boolean isOpen() {
        return open;
    }

    @Override
    public String toString() {
        //TODO
        return "Door is ";
    }
}
