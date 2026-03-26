package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@DiscriminatorValue("Organizer")
public class Organizer extends Person implements Serializable {

    private Long id;
    private Collection<Concert> concerts;
    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public Organizer() {}

    @OneToMany(mappedBy = "organizer")
    public Collection<Concert> getConcert() {
        return concerts;
    }

    public void setConcert(Collection<Concert> concerts) {
        this.concerts = concerts;
    }
}
