package com.learn.spring.integration.jmx.activator;

import org.springframework.messaging.Message;

public class CommonLoggerServiceActivator {
    public void logPolled(Message<?> msg) {
        System.out.println( "Order Id ::" + msg.getPayload().toString() + " is being processed");
    }
}
