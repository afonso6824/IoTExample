package core.Modules;

import com.google.inject.AbstractModule;
import core.Services.MessageService;

public class WhatsappModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MessageService.class).to(WhatsappService.class);
    }
}
