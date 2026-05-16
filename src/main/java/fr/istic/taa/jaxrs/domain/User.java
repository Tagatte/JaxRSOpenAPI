package fr.istic.taa.jaxrs.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Collection;

@Entity
@DiscriminatorValue("User")
public class User extends Person implements Serializable {

    @JsonIgnore
    private Collection<Ticket> tickets;
    public User() {}


//    @OneToMany(mappedBy = "user")
//    public Collection<Ticket> getTickets() {
//        return tickets;
//    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
