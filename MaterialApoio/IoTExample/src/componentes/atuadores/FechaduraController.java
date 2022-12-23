package componentes.atuadores;

public class FechaduraController {
    private int code;
    private Door door;

    public FechaduraController(int code){
        this.code = code;
        this.door = new Door();

    }
    public  FechaduraController(){
        this(1234);
    }
    public void openDoor() {
        door.openDoor();
    }
    public void closeDoor() {
        door.closeDoor();
    }

    @Override
    public String toString() {
        if (door.isOpen()){
            return "Door is open";
        }else {
            return "Door is closed";
        }
    }
}
