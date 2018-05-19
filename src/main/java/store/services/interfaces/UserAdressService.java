package store.services.interfaces;

import store.dao.interfaces.UserAdressDAO;
import store.dto.UserAdressDTO;
import store.dto.UserDTO;
import store.entities.User;
import store.exceptions.DAOException;

import java.util.List;


/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface UserAdressService{
    /**
     * Getting user adress entity by user
     *
     * @param userId id of entity user for getting
     * @return user adress by Yser
     * @throws DAOException if user adress not found
     */
    public UserAdressDTO getUserAdressByUserId(String userId);

    public void createEntity(UserDTO userDTO, UserAdressDTO userAdressDTO) throws DAOException;

    public UserAdressDTO getEntityById(Integer id) throws DAOException;

    public void deleteEntity(UserAdressDTO userAdressDTO) throws DAOException;

    public void updateEntity(UserDTO userDTO, UserAdressDTO userAdressDTO) throws DAOException;

    public List<UserAdressDTO> getAll() throws DAOException;
}
