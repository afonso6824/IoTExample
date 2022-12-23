package core;

public class GestorAvisos {

    private boolean problemasDeAudiçao;
    public GestorAvisos(){

    }

    public void sendAviso(String msg){
        System.out.println("ecrâ: " + msg);
        if (problemasDeAudiçao){
            System.out.println("Sinais de luzes ");
        }
        else {
            System.out.println("Voz Sintetizada: " + msg);
        }
    }
}
