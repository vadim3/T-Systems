package store.dao.implementation;

import org.springframework.stereotype.Repository;
import store.dao.interfaces.UserDAO;
import store.entities.AccessLevel;
import store.entities.User;
import store.exceptions.DAOException;
import store.exceptions.UserNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("userDAO")
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Getting user entity by number
     *
     * @param number entity for getting
     * @return user with adjusted number
     * @throws UserNotFoundException if user not found
     */
    @Override
    public User getUserByNumber(String number) throws UserNotFoundException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.phoneNumber=:phoneNumber")
                    .setParameter("phoneNumber", number);
            return (User) query.getSingleResult();
        } catch (PersistenceException e) {
            throw new UserNotFoundException("User " + number + " wasn't found", e);
        }

    }

    /**
     * Getting user entity by email
     *
     * @param eMail entity for getting
     * @return user with adjusted number
     * @throws UserNotFoundException if user not found
     */
    @Override
    public User getUserByeMail(String eMail) throws UserNotFoundException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.email=:eMail")
                    .setParameter("eMail", eMail);
            return (User) query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new UserNotFoundException("User with email " + eMail + " not found!", ex);
        }
    }


    @Override
    public void create(User entity) throws DAOException {
            entityManager.persist(entity);
    }
}
