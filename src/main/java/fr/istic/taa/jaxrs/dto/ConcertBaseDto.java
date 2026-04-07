package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Organizer;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.wildfly.common.annotation.NotNull;

public abstract class ConcertBaseDto {
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    @NotEmpty
    private String Location;
    private String musicalGenre;
    @NotNull
    private int placeNumber;
    @NotNull
    @Min(1)
    @Max(5)
    private int popularity;
    @NotNull
    private Long price;
    @NotNull
    private Long organizerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicalGenre() {
        return musicalGenre;
    }

    public void setMusicalGenre(String musicalGenre) {
        this.musicalGenre = musicalGenre;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }
}
