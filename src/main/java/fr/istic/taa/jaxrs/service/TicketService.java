package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import java.util.Date;
import java.util.List;

public class TicketService {
    private final TicketDao ticketDao = new TicketDao();

    public List<Ticket> getAllTickets() {
        return ticketDao.findAll();
    }

    public Ticket getTicket(long id) {
        return ticketDao.findOne(id);
    }

    public void deleteTicket(long id) {
        ticketDao.deleteById(id);
    }

    // Méthode métier — annuler un ticket
    public Ticket cancelTicket(long id) {
        Ticket ticket = getTicket(id);
        ticket.setCanceled(true);
        ticket.setCancelDate(new Date());
        ticketDao.update(ticket);
        return ticket;
    }

    // Méthode métier — rembourser un ticket
    public Ticket refundTicket(long id) {
        Ticket ticket = getTicket(id);
        ticket.setRefunded(true);
        ticket.setRefundDate(new Date());
        ticketDao.update(ticket);
        return ticket;
    }
}