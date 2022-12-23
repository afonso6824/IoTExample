package core.Modules;

import core.Services.MessageService;

public class WhatsappService extends MessageService {
    @Override
    public void sendMessage(String msg) {
        System.out.println("Whatsapp" + msg);
    }
}
