package core.Services;

import com.google.inject.AbstractModule;

public class SMSModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageService.class).to(SMSService.class);
    }
}
