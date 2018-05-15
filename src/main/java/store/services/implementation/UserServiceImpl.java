package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.AccessLevelDAO;
import store.dao.interfaces.UserDAO;
import store.dto.UserDTO;
import store.entities.Order;
import store.entities.Product;
import store.entities.User;
import store.exceptions.DAOException;
import store.exceptions.UserNotFoundException;
import store.services.interfaces.EntityDTOMapper;
import store.services.interfaces.UserService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AccessLevelDAO accessLevelDAO;

    @Autowired
    private EntityDTOMapper entityDTOMapper;

    @Override
    @Transactional
    public UserDTO getUserByeMail(String eMail) throws UserNotFoundException {
        return entityDTOMapper.mapDTOFromUser(userDAO.getUserByeMail(eMail));
    }

    @Override
    @Transactional
    public UserDTO getUserByNumber(String number) throws UserNotFoundException {
        return entityDTOMapper.mapDTOFromUser(userDAO.getUserByNumber(number));
    }

    @Override
    public UserDTO createUser(String eMail, String phoneNumber, String password) {
        return entityDTOMapper.mapDTOFromUser(new User(eMail, phoneNumber, password, accessLevelDAO.read(1)));
    }

    @Override
    @Transactional
    public void createEntity(UserDTO userDTO) throws DAOException {
        User user = entityDTOMapper.mapUserFromDTO(userDTO);
        if (!isUserExists(user)) {
            userDAO.create(user);
        }
    }

    @Override
    @Transactional
    public UserDTO getEntityById(Integer id) throws DAOException {
        return entityDTOMapper.mapDTOFromUser(userDAO.read(id));
    }

    @Override
    @Transactional
    public void updateEntity(UserDTO entity) throws DAOException {
        userDAO.update(entityDTOMapper.mapUserFromDTO(entity));
    }

    @Override
    @Transactional
    public void deleteEntity(UserDTO entity) throws DAOException {
        userDAO.delete(entityDTOMapper.mapUserFromDTO(entity));
    }

    @Override
    @Transactional
    public List<UserDTO> getAll() throws DAOException {
        List<User> userList = userDAO.getAll();
        return userList.stream().map(user -> entityDTOMapper.mapDTOFromUser(user)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Map<UserDTO, Double> getTopTenUsers() throws UserNotFoundException {
        Map<User, Double> map = new HashMap<>();
        Map<UserDTO, Double> resultMap = new LinkedHashMap<>();

        for (User user : userDAO.getAll()){
            double sum = 0;
            for (Order order : user.getOrders()){
                for (Product product : order.getProducts()){
                    sum += product.getPrice();
                }
            }
            map.put(user, sum);
        }

        User user = new User();
        for (int i = 0; i < 10; i++){
            double max = 0;
            if (map.isEmpty()) break;
            for (Map.Entry<User, Double> entry : map.entrySet()){
                if (entry.getValue() >= max){
                    max = entry.getValue();
                    user = entry.getKey();
                }
            }
            resultMap.put(entityDTOMapper.mapDTOFromUser(user), max);
            map.remove(user);
        }

        return resultMap;
    }


    public boolean isUserExists(User user) {
        try {
            return getUserByeMail(user.getEmail()) != null ? true : false;
        } catch (UserNotFoundException ex) {
            return false;
        }
    }
}
