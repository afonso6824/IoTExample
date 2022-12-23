package core.Services;

import core.Services.MessageService;

public class SMSService extends MessageService {
    @Override
    public void sendMessage(String msg) {
        System.out.println("SMS" + msg);
    }
}
