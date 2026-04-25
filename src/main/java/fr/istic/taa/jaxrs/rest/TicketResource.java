package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.service.TicketService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("tickets")
@Produces(MediaType.APPLICATION_JSON)
public class TicketResource {
    private final TicketService ticketService = new TicketService();

    @GET
    @Path("/")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GET
    @Path("/{id}")
    public Ticket getTicket(@PathParam("id") long id) {
        return ticketService.getTicket(id);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTicket(@PathParam("id") long id) {
        ticketService.deleteTicket(id);
    }

    @PUT
    @Path("/{id}/cancel")
    public Ticket cancelTicket(@PathParam("id") long id) {
        return ticketService.cancelTicket(id);
    }

    @PUT
    @Path("/{id}/refund")
    public Ticket refundTicket(@PathParam("id") long id) {
        return ticketService.refundTicket(id);
    }
}