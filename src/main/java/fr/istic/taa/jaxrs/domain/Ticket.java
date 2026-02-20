package fr.istic.taa.jaxrs.domain;
import jakarta.persistence.*;


import java.util.Date;

@Entity
public class Ticket {

    private Long id;
    private Long prix;
    private boolean estAnnule;
    private boolean estRembourse;
    private Date dateAchat;
    private Date dateAnnulation;
    private Date dateRemboursement;
    private Utilisateur utilisateur;
    private Concert concert;

    public Ticket() {

    }

    @Id
    @GeneratedValue()
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrix() {
        return prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public boolean isEstAnnule() {
        return estAnnule;
    }

    public void setEstAnnule(boolean estAnnule) {
        this.estAnnule = estAnnule;
    }

    public boolean isEstRembourse() {
        return estRembourse;
    }

    public void setEstRembourse(boolean estRembourse) {
        this.estRembourse = estRembourse;
    }

    @Temporal(TemporalType.DATE)
    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
    @Temporal(TemporalType.DATE)
    public Date getDateAnnulation() {
        return dateAnnulation;
    }

    public void setDateAnnulation(Date dateAnnulation) {
        this.dateAnnulation = dateAnnulation;
    }

    @Temporal(TemporalType.DATE)
    public Date getDateRemboursement() {
        return dateRemboursement;
    }

    public void setDateRemboursement(Date dateRemboursement) {
        this.dateRemboursement = dateRemboursement;
    }

    @ManyToOne
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    @ManyToOne
    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
