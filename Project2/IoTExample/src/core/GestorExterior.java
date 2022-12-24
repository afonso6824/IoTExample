package core;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import core.Services.MessageService;
import core.Services.SMSModule;

public class GestorExterior {

    @Inject
    private MessageService messageService;

    public GestorExterior(){
        Injector injector = Guice.createInjector(new SMSModule());
        messageService = injector.getInstance(MessageService.class);
    }
    public void sendMessage(String msg){

    }
}
