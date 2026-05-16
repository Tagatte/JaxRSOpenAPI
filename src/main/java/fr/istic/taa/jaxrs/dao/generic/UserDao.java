package fr.istic.taa.jaxrs.dao.generic;
import fr.istic.taa.jaxrs.domain.User;

public class UserDao  extends AbstractJpaDao<Long, User>{
    public UserDao() {
        super(User.class);
    }

    public int countUsers() {
        return entityManager.createQuery(
                        "SELECT COUNT(p) FROM Person p WHERE TYPE(p) = User",
                        Integer.class)
                .getSingleResult();
    }
}
