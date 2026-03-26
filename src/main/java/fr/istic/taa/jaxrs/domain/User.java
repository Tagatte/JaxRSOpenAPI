package fr.istic.taa.jaxrs.domain;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Collection;

@Entity
@DiscriminatorValue("User")
public class User extends Person implements Serializable {

    private Long id;
    private Collection<Ticket> tickets;
    public User() {}

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "user")
    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
