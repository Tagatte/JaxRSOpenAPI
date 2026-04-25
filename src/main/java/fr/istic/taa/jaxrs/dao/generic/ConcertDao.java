package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

import java.util.List;

public class ConcertDao  extends AbstractJpaDao<Long, Concert>{
    public ConcertDao() {
        super(Concert.class);
    }

    public List<Concert> searchConcerts(String searchQ) {
        String q ="SELECT DISTINCT c FROM Concert c " +
                        "LEFT JOIN c.artists a " +
                        "WHERE LOWER(a.firstName) LIKE LOWER(:searchQ) " +
                        "OR LOWER(a.lastName) LIKE LOWER(:searchQ) " +
                        "OR LOWER(c.name) LIKE LOWER(:searchQ) " +
                        "OR LOWER(c.location) LIKE LOWER(:searchQ) "+
                        "OR LOWER(c.musicalGenre) LIKE LOWER(:searchQ) " +
                        "OR FUNCTION('TO_CHAR', c.date, 'YYYY-MM-DD') = :searchQ";

        return entityManager.createQuery(q, Concert.class).setParameter("searchQ", "%" + searchQ + "%").getResultList();
    }
}
