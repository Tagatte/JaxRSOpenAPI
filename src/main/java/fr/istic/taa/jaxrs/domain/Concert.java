package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
public class Concert {
    private Long id;
    private String nom;
    private Date date;
    private int heureDebut;
    private int heureFin;
    private String lieu;
    private Long prix;
    private String genreMusical;
    private String popularite;
    private Long nbPlace;
    private String description;
    private boolean estValide;
    private boolean estAnnule;
    private boolean estSupprime;
    private Collection<Ticket> tickets;

    @Id
    @GeneratedValue()
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public Long getPrix() {
        return prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getGenreMusical() {
        return genreMusical;
    }

    public void setGenreMusical(String genreMusical) {
        this.genreMusical = genreMusical;
    }

    public String getPopularite() {
        return popularite;
    }

    public void setPopularite(String popularite) {
        this.popularite = popularite;
    }

    public Long getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Long nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public boolean isEstAnnule() {
        return estAnnule;
    }

    public void setEstAnnule(boolean estAnnule) {
        this.estAnnule = estAnnule;
    }

    public boolean isEstSupprime() {
        return estSupprime;
    }

    public void setEstSupprime(boolean estSupprime) {
        this.estSupprime = estSupprime;
    }
    @OneToMany(mappedBy = "concert")
    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
