package com.learn.ws.service;

import com.learn.ws.model.TicketConfirmation;

import java.util.Date;

public interface TicketService {
    public TicketConfirmation order(String filmId, Date sessionDate, int quantity);
}
