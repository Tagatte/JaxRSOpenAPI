package fr.istic.taa.jaxrs.dto;

public class TicketCreateDto {
    private Long concertId;
    private Long userId;

    public Long getConcertId() { return concertId; }
    public void setConcertId(Long concertId) { this.concertId = concertId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}