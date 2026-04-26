package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Concert.findValidated",
                query = "SELECT c FROM Concert c WHERE c.isValidated = true"),
        @NamedQuery(name = "Concert.findByLocation",
                query = "SELECT c FROM Concert c WHERE c.location = :location"),
        @NamedQuery(name = "Concert.findByDate",
                query = "SELECT c FROM Concert c WHERE c.date = :date"),
        @NamedQuery(name = "Concert.findAllOrderByPopularity",
                query = "SELECT c FROM Concert c ORDER BY c.popularity DESC"),
        @NamedQuery(name = "Concert.findAllOrderByPrice",
                query = "SELECT c FROM Concert c ORDER BY c.price ASC"),
        @NamedQuery(name = "Concert.findAllOrderByDate",
                query = "SELECT c FROM Concert c ORDER BY c.date ASC")
})

public class Concert implements Serializable {
    private Long id;
    private String name;
    private Date date;
    private int startTime;
    private int endTime;
    private String location;
    private Long price;
    private String musicalGenre;
    private int popularity;
    private int placeNumber;
    private String description;
    private boolean isCanceled;
    private boolean isDeleted;
    private boolean isValidated;
    private Date validationDate;
    private Admin admin;
    private Organizer organizer;
    private Collection<Ticket> tickets;
    private Collection<Artist> artists;
    private Collection<Notification> notifications;


    @Id
    @GeneratedValue()
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMusicalGenre() {
        return musicalGenre;
    }

    public void setMusicalGenre(String musicalGenre) {
        this.musicalGenre = musicalGenre;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsValidated() {
        return isValidated;
    }

    public void setIsValidated(boolean isValidated) {
        this.isValidated = isValidated;
    }

    public boolean isIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Temporal(TemporalType.DATE)
    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }
    @OneToMany(mappedBy = "concert")
    public Collection<Ticket> getTickets() {
        return tickets;
    }


    @ManyToOne
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @ManyToOne
    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    @ManyToMany(mappedBy = "concerts")
    public Collection<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Collection<Artist> artists) {
        this.artists = artists;
    }

    @OneToMany(mappedBy = "concert")
    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }

}
