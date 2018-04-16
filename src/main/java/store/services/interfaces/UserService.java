package store.services.interfaces;

import store.entities.User;
import store.exceptions.UserNotFoundException;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/


public interface UserService extends GenericService<User, Integer>{

    /**
     * Getting user entity by email
     *
     * @param eMail entity for getting
     * @return user with adjusted email
     * @throws UserNotFoundException if user not found
     */
    public User getUserByeMail(String eMail) throws UserNotFoundException;

    /**
     * Getting user entity by number
     *
     * @param number entity for getting
     * @return user with adjusted number
     * @throws UserNotFoundException if user not found
     */
    public User getUserByNumber(String number) throws UserNotFoundException;
}
