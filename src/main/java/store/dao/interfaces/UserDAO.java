package store.dao.interfaces;

import store.entities.User;
import store.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface UserDAO extends GenericDAO<User, Integer>{
    /**
     * Getting user entity by number
     *
     * @param number entity for getting
     * @return user with adjusted number
     * @throws UserNotFoundException if user not found
     */
    public User getUserByNumber(String number) throws UserNotFoundException;

    /**
     * Getting user entity by email
     *
     * @param eMail entity for getting
     * @return user with adjusted number
     * @throws UserNotFoundException if user not found
     */
    public User getUserByeMail(String eMail) throws UserNotFoundException;


}
