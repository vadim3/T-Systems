package store.dao.interfaces;

import store.entities.User;
import store.entities.UserAdress;
import store.exceptions.DAOException;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface UserAdressDAO extends GenericDAO<UserAdress, Integer>{
    /**
     * Getting user adress entity by user
     *
     * @param user entity user for getting
     * @return user adress by Yser
     * @throws DAOException if user adress not found
     */
    public UserAdress getUserAdressByUser(User user) throws DAOException;

}
