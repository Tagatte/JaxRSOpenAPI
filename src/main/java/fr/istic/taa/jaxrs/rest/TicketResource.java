package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketCreateDto;
import fr.istic.taa.jaxrs.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @POST
    @Path("/buy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Acheter un ticket", description = "Permet à un utilisateur d'acheter un ticket pour un concert")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ticket acheté avec succès"),
            @ApiResponse(responseCode = "400", description = "Concert annulé ou plus de places disponibles"),
            @ApiResponse(responseCode = "404", description = "Concert ou utilisateur non trouvé")
    })
    public Ticket buyTicket(TicketCreateDto ticketCreateDto) {
        return ticketService.buyTicket(ticketCreateDto);
    }
}