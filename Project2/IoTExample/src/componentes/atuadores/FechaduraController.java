package componentes.atuadores;

import utils.I18N;
import utils.Messages;

import static utils.Messages.DOOR_CLOSED;
import static utils.Messages.DOOR_OPEN;

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
        System.out.println(I18N.getString(DOOR_OPEN));
    }
    public void closeDoor() {
        door.closeDoor();
        System.out.println(I18N.getString(DOOR_CLOSED));
    }

}
