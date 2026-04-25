package fr.istic.taa.jaxrs.dto;

public class TicketTransferDto {
    private Long newUserId;

    public Long getNewUserId() { return newUserId; }
    public void setNewUserId(Long newUserId) { this.newUserId = newUserId; }
}