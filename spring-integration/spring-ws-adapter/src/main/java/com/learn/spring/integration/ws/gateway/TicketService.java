package com.learn.spring.integration.ws.gateway;

import com.learn.spring.integration.ws.model.TicketRequest;
import com.learn.spring.integration.ws.model.TicketResponse;
import org.springframework.integration.annotation.Gateway;

public interface TicketService {

    /**
     * Entry to the messaging system.
     * All invocations to this method will be
     * intercepted and sent to the SI "system entry" gateway
     *
     * @param request
     */
    @Gateway
    public TicketResponse invoke(TicketRequest request);
}
