package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.UserAdressDAO;
import store.dao.interfaces.UserDAO;
import store.dto.UserAdressDTO;
import store.dto.UserDTO;
import store.entities.User;
import store.entities.UserAdress;
import store.exceptions.DAOException;
import store.services.interfaces.EntityDTOMapper;
import store.services.interfaces.UserAdressService;


import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Service("userAdressService")
public class UserAdressServiceImpl implements UserAdressService {
    @Autowired
    private EntityDTOMapper entityDTOMapper;

    @Autowired
    private UserAdressDAO userAdressDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public UserAdressDTO getUserAdressByUserId(String userId) {
        try {
            User user = userDAO.read(Integer.valueOf(userId));
            return entityDTOMapper.mapDTOFromUserAdress(userAdressDAO.getUserAdressByUser(user));
        } catch (Exception ex){
            return new UserAdressDTO();
        }
    }

    @Override
    @Transactional
    public void createEntity(UserDTO userDTO, UserAdressDTO userAdressDTO) throws DAOException {
        UserAdress userAdress = new UserAdress();
        entityDTOMapper.mapUserAdressFromDTO(userAdress, userAdressDTO);
        User user = userDAO.read(Integer.valueOf(userDTO.getUserId()));
        user.setUserAdress(userAdress);
        userDAO.update(user);
    }

    @Override
    @Transactional
    public UserAdressDTO getEntityById(Integer id) throws DAOException {
        return entityDTOMapper.mapDTOFromUserAdress(userAdressDAO.read(id));
    }

    @Transactional
    public void updateEntity(UserDTO userDTO, UserAdressDTO userAdressDTO) throws DAOException {
        UserAdress userAdress = userAdressDAO.read(userAdressDTO.getAdressId());
        entityDTOMapper.mapUserAdressFromDTO(userAdress, userAdressDTO);
        User user = userDAO.read(Integer.valueOf(userDTO.getUserId()));
        user.setUserAdress(userAdress);
        userDAO.update(user);
    }

    @Override
    public void deleteEntity(UserAdressDTO userAdressDTO) throws DAOException {
        UserAdress userAdress = userAdressDAO.read(userAdressDTO.getAdressId());
        entityDTOMapper.mapUserAdressFromDTO(userAdress, userAdressDTO);
        userAdressDAO.update(userAdress);
    }

    @Override
    public List<UserAdressDTO> getAll() throws DAOException {
        return null;
    }
}
