package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.ConcertDao;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.dto.ConcertCreateDto;
import fr.istic.taa.jaxrs.dto.ConcertUpdateDto;
import fr.istic.taa.jaxrs.service.ConcertService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.sql.Update;

import java.util.List;

@Path("/concerts")
@Produces(MediaType.APPLICATION_JSON)
public class ConcertResource {

    private static final ConcertService concertService = new ConcertService();

    @GET
    @Path("/")
    public List<Concert> getConcerts() {
        return concertService.getConcerts();
    }

    @GET
    @Path("/search")
    public List<Concert> searchConcerts(@QueryParam("query") String name) {
        return null;
    }

    @GET
    @Path("/{id}")
    public Concert getConcert(@PathParam("id") long id) {
        return  concertService.getConcert(id);
    }

    @POST
    @Path("/")
    public Concert createConcert(ConcertCreateDto concertCreateDto) {
      return concertService.createConcert(concertCreateDto);
    }

    @PUT
    @Path("/{id}")
    public Concert updateConcert(@PathParam("id") long id, ConcertUpdateDto concertUpdateDto) {
        return  concertService.updateConcert(id, concertUpdateDto);
    }

    @DELETE
    @Path("/{id}")
    public void deleteConcert(@PathParam("id") long id) {
        concertService.deleteConcert(id);
    }


}
