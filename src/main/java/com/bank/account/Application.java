package com.bank.account;

import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
    public Application() {
        TransferApplicationComponent component = DaggerTransferApplicationComponent.create();
        register(component.getAccountResource());
        register(component.getAccountService());
    }
}
