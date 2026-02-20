package fr.istic.taa.jaxrs.domain;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("Organisateur")
public class Organisateur extends Personne{

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public Organisateur() {}
}
