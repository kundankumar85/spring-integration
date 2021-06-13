package com.learn.ws.controller;

import com.learn.ws.model.TicketConfirmation;
import com.learn.ws.schema.TicketRequest;
import com.learn.ws.schema.TicketResponse;
import com.learn.ws.service.TicketService;
import com.learn.ws.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

@Scope(value = "session")
@Endpoint
public class TicketEndpoint {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private int retries;

    @Autowired
    private TicketService ticketService;

    private static final String NAMESPACE_URI = "http://learn/ws/ticket-producing-web-service";

    @PayloadRoot(localPart="ticketRequest", namespace=NAMESPACE_URI)
    public @ResponsePayload   TicketResponse order(@RequestPayload TicketRequest ticketRequest) throws InterruptedException {
        Calendar sessionDate = Calendar.getInstance();
        sessionDate.set(2013, 9, 26);

        TicketConfirmation confirmation = ticketService.order(
                ticketRequest.getFilmId(), DateUtils.toDate(ticketRequest.getSessionDate()), ticketRequest.getQuantity().intValue());

        TicketResponse response = buildResponse(confirmation);


        retries++;
        logger.info("Retry attempt: {}",retries);
        if (retries < 3) {

            throw new RuntimeException("not enough retries");
        }
       /* else {
            retries = 0;
        }*/

        //Thread.sleep(8000);
        return response;
    }

    private TicketResponse buildResponse(TicketConfirmation confirmation) {
        TicketResponse response = new TicketResponse();
        response.setConfirmationId(confirmation.getConfirmationId());
        response.setFilmId(confirmation.getFilmId());
        response.setSessionDate(DateUtils.convertDate(confirmation.getSessionDate()));
        BigInteger quantity = new BigInteger(Integer.toString(confirmation.getQuantity()));
        response.setQuantity(quantity);
        BigDecimal amount = new BigDecimal(Float.toString(confirmation.getAmount()));
        response.setAmount(amount);

        return response;
    }
}
