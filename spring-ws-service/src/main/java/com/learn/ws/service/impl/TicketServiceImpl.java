package com.learn.ws.service.impl;

import com.learn.ws.model.TicketConfirmation;
import com.learn.ws.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public TicketConfirmation order(String filmId, Date sessionDate, int quantity) {
        float amount = 5.95f * quantity;
        TicketConfirmation confirmation = new TicketConfirmation(filmId, sessionDate, quantity, amount);

        return confirmation;
    }
}
