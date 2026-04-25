package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketCreateDto;
import fr.istic.taa.jaxrs.dto.TicketTransferDto;
import fr.istic.taa.jaxrs.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
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


    @GET
    @Path("/user/{userId}")
    @Operation(summary = "Tickets d'un utilisateur", description = "Retourne tous les tickets achetés par un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tickets trouvés"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    })
    public List<Ticket> getTicketsByUser(
            @Parameter(description = "ID de l'utilisateur", required = true)
            @PathParam("userId") long userId) {
        return ticketService.getTicketsByUser(userId);
    }

    @PUT
    @Path("/{id}/transfer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Transférer un ticket", description = "Transfère un ticket à un autre utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ticket transféré avec succès"),
            @ApiResponse(responseCode = "400", description = "Ticket annulé, impossible de transférer"),
            @ApiResponse(responseCode = "404", description = "Ticket ou utilisateur non trouvé")
    })
    public Ticket transferTicket(
            @Parameter(description = "ID du ticket", required = true)
            @PathParam("id") long id,
            @Parameter(description = "ID du nouvel utilisateur", required = true)
            TicketTransferDto ticketTransferDto) {
        return ticketService.transferTicket(id, ticketTransferDto.getNewUserId());
    }
}