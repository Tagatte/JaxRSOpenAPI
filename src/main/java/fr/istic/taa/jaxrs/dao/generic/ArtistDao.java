package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.domain.Artist;

public class ArtistDao extends AbstractJpaDao<Long, Artist> {
    public ArtistDao() {
        super(Artist.class);
    }

    public int countArtists() {
        return entityManager.createQuery(
                        "SELECT COUNT(p) FROM Person p WHERE TYPE(p) = Artist",
                        Integer.class)
                .getSingleResult();
    }
}
