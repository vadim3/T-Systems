package store.dao.implementation;

import org.springframework.stereotype.Repository;
import store.dao.interfaces.UserAdressDAO;
import store.entities.User;
import store.entities.UserAdress;
import store.exceptions.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("userAdressDAO")
public class UserAdressDAOImpl extends GenericDAOImpl<UserAdress, Integer> implements UserAdressDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserAdress getUserAdressByUser(User user) throws DAOException {
        try {
            Query query = entityManager.createQuery("select u from UserAdress u where u.adressId=:adressId")
                    .setParameter("adressId", user.getUserAdress().getAdressId());
            return (UserAdress) query.getSingleResult();
        } catch (PersistenceException e) {
            throw new DAOException("User Adress for User with id:" + user.getUserId() + " still not exist", e);
        }
    }
}
