package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.service.UserService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService userService = new UserService();

    @GET
    @Path("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
