package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Ticket;
import java.util.List;

public class TicketDao extends  AbstractJpaDao<Long, Ticket>{

    public TicketDao() {
        super(Ticket.class);
    }
    public List<Ticket> findByUser(long userId) {
        return entityManager.createQuery(
                        "SELECT t FROM fr.istic.taa.jaxrs.domain.Ticket t WHERE t.user.id = :userId",
                        Ticket.class)
                .setParameter("userId", userId)
                .getResultList();
    }

}
