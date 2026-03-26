package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@DiscriminatorValue("Artist")
public class Artist extends Person implements Serializable {

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

    public Artist() {}

    @ManyToMany
    public Collection<Concert> getConcerts() {
        return concerts;
    }


    public void setConcerts(Collection<Concert> concerts) {
        this.concerts = concerts;
    }
}
