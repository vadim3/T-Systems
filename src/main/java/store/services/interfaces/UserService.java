package store.services.interfaces;

import store.dto.UserDTO;
import store.entities.User;
import store.exceptions.DAOException;
import store.exceptions.UserNotFoundException;

import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/


public interface UserService extends GenericService<UserDTO, Integer>{

    /**
     * Getting user entity by email
     *
     * @param eMail entity for getting
     * @return user with adjusted email
     * @throws UserNotFoundException if user not found
     */
    public UserDTO getUserByeMail(String eMail) throws UserNotFoundException;

    /**
     * Getting user entity by number
     *
     * @param number entity for getting
     * @return user with adjusted number
     * @throws UserNotFoundException if user not found
     */
    public UserDTO getUserByNumber(String number) throws UserNotFoundException;

    /**
     * Creating new User
     *
     * @param phoneNumber phone number for getting
     * @param eMail for getting
     * @param password password
     * @return new user
     */
    public void createUser(String eMail, String phoneNumber, String password);

    public Map<UserDTO, Double> getTopTenUsers() throws UserNotFoundException;

    public void updatePassword(UserDTO userDTO, String password) throws UserNotFoundException;

    String getUserPassword(UserDTO userDTO) throws UserNotFoundException;
}
