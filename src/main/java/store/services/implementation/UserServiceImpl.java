package store.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.AccessLevelDAO;
import store.dao.interfaces.OrderDAO;
import store.dao.interfaces.UserDAO;
import store.dto.OrderDTO;
import store.dto.UserDTO;
import store.entities.Order;
import store.entities.Product;
import store.entities.User;
import store.exceptions.DAOException;
import store.exceptions.UserNotFoundException;
import store.services.interfaces.EntityDTOMapper;
import store.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AccessLevelDAO accessLevelDAO;

    @Autowired
    private EntityDTOMapper entityDTOMapper;

    @Autowired
    private OrderDAO orderDAO;

    @Override
    @Transactional
    public UserDTO getUserByeMail(String eMail) throws UserNotFoundException {

        User user = userDAO.getUserByeMail(eMail);
        UserDTO userDTO = entityDTOMapper.mapDTOFromUser(user);
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO getUserByNumber(String number) throws UserNotFoundException {
        return entityDTOMapper.mapDTOFromUser(userDAO.getUserByNumber(number));
    }

    @Override
    @Transactional
    public void createUser(String eMail, String phoneNumber, String password) {
        User user = new User(eMail, phoneNumber, password, accessLevelDAO.read(1));
        userDAO.create(user);

    }

    @Override
    @Transactional
    public void createEntity(UserDTO userDTO) throws DAOException {
        User user = new User();
        if (userDTO.getUserId() != null) {
            user = userDAO.read(Integer.valueOf(userDTO.getUserId()));
        }
        entityDTOMapper.mapUserFromDTO(user, userDTO);
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
    public void updateEntity(UserDTO userDTO) throws DAOException {
        User user;
        if (userDTO.getUserId() == null) {
            user = new User();
        } else {
            user = userDAO.read(Integer.valueOf(userDTO.getUserId()));
        }
        entityDTOMapper.mapUserFromDTO(user, userDTO);
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void updatePassword(UserDTO userDTO, String password) throws DAOException {
        User user = (userDTO.getUserId() == null)? new User(): userDAO.read(Integer.valueOf(userDTO.getUserId()));
        entityDTOMapper.mapUserFromDTO(user, userDTO);
        user.setPassword(password);
        userDAO.update(user);
    }

    @Override
    @Transactional
    public String getUserPassword(UserDTO userDTO) throws UserNotFoundException {
        User user = userDAO.read(Integer.valueOf(userDTO.getUserId()));
        return user.getPassword();
    }


    @Override
    @Transactional
    public void deleteEntity(UserDTO userDTO) throws DAOException {
        userDAO.delete(userDAO.read(Integer.valueOf(userDTO.getUserId())));
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

        for (User user : userDAO.getAll()) {
            double sum = 0;
            for (Order order : user.getOrders()) {
                for (Product product : order.getProducts()) {
                    sum += product.getPrice();
                }
            }
            map.put(user, sum);
        }

        User user = new User();
        for (int i = 0; i < 10; i++) {
            double max = 0;
            if (map.isEmpty()) break;
            for (Map.Entry<User, Double> entry : map.entrySet()) {
                if (entry.getValue() >= max) {
                    max = entry.getValue();
                    user = entry.getKey();
                }
            }
            resultMap.put(entityDTOMapper.mapDTOFromUser(user), max);
            map.remove(user);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public UserDTO getUserByOrder(OrderDTO orderDTO) throws UserNotFoundException{
        if (orderDTO == null || orderDTO.getOrderId() == 0){
            return null;
        }
        User user = orderDAO.getOrderById(orderDTO.getOrderId()).getUser();
        return entityDTOMapper.mapDTOFromUser(user);
    }

    @Override
    @Transactional
    public int getAccessLevelIdByUser(UserDTO userDTO) {
        return userDAO.read(Integer.valueOf(userDTO.getUserId())).getAccessLevel().getAccessLevelId();
    }

    public boolean isUserExists(User user) {
        try {
            return getUserByeMail(user.getEmail()) != null ? true : false;
        } catch (UserNotFoundException ex) {
            return false;
        }
    }
}
