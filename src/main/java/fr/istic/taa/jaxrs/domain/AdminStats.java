package fr.istic.taa.jaxrs.domain;

public class AdminStats {
    private int totalConcerts;
    private int totalUsers;
    private int totalArtists;
    private int totalOrganizers;
    private int totalTickets;

    public int getTotalConcerts() { return totalConcerts; }
    public void setTotalConcerts(int totalConcerts) { this.totalConcerts = totalConcerts; }

    public int getTotalUsers() { return totalUsers; }
    public void setTotalUsers(int totalUsers) { this.totalUsers = totalUsers; }

    public int getTotalArtists() { return totalArtists; }
    public void setTotalArtists(int totalArtists) { this.totalArtists = totalArtists; }

    public int getTotalOrganizers() { return totalOrganizers; }
    public void setTotalOrganizers(int totalOrganizers) { this.totalOrganizers = totalOrganizers; }

    public int getTotalTickets() { return totalTickets; }
    public void setTotalTickets(int totalTickets) { this.totalTickets = totalTickets; }
}