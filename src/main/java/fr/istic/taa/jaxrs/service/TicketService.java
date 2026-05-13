package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.generic.ConcertDao;
import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.TicketCreateDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.Date;
import java.util.List;

public class TicketService {
    private final TicketDao ticketDao = new TicketDao();
    private final ConcertDao concertDao = new ConcertDao();
    private final UserDao userDao = new UserDao();

    public int countTickets(){
        return ticketDao.countTickets();
    }
    public List<Ticket> getAllTickets() {
        return ticketDao.findAll();
    }

    public Ticket getTicket(long id) {
        return ticketDao.findOne(id);
    }

    public void deleteTicket(long id) {
        ticketDao.deleteById(id);
    }

    // Méthode métier — acheter un ticket
    public Ticket buyTicket(TicketCreateDto ticketCreateDto) {
        Concert concert = concertDao.findOne(ticketCreateDto.getConcertId());
        User user = userDao.findOne(ticketCreateDto.getUserId());

        if (concert == null) throw new NotFoundException("Concert non trouvé");
        if (user == null) throw new NotFoundException("User non trouvé");
        if (concert.isIsCanceled()) throw new BadRequestException("Concert annulé");
        if (concert.getPlaceNumber() <= 0) throw new BadRequestException("Plus de places disponibles");

        Ticket ticket = new Ticket();
        ticket.setPrice(concert.getPrice());
        ticket.setDate(new Date());
        ticket.setCanceled(false);
        ticket.setRefunded(false);
        ticket.setConcert(concert);
        ticket.setUser(user);

        // Décrémente le nombre de places
        concert.setPlaceNumber(concert.getPlaceNumber() - 1);
        concertDao.update(concert);

        ticketDao.save(ticket);
        return ticket;
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

    // Tickets par utilisateur
    public List<Ticket> getTicketsByUser(long userId) {
        User user = userDao.findOne(userId);
        if (user == null) throw new NotFoundException("User non trouvé");
        return ticketDao.findByUser(userId);
    }

    // Transfert de ticket
    public Ticket transferTicket(long ticketId, Long newUserId) {
        Ticket ticket = getTicket(ticketId);
        if (ticket == null) throw new NotFoundException("Ticket non trouvé");
        if (ticket.isCanceled()) throw new BadRequestException("Ticket annulé, impossible de transférer");

        User newUser = userDao.findOne(newUserId);
        if (newUser == null) throw new NotFoundException("Nouvel utilisateur non trouvé");

        ticket.setUser(newUser);
        ticketDao.update(ticket);
        return ticket;
    }
}