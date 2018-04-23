package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.AccessLevelDAO;
import store.dao.interfaces.UserDAO;
import store.entities.Order;
import store.entities.Product;
import store.entities.User;
import store.exceptions.DAOException;
import store.exceptions.UserNotFoundException;
import store.services.interfaces.UserService;

import java.util.*;

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


    @Override
    @Transactional
    public User getUserByeMail(String eMail) throws UserNotFoundException {
        return userDAO.getUserByeMail(eMail);
    }

    @Override
    @Transactional
    public User getUserByNumber(String number) throws UserNotFoundException {
        return userDAO.getUserByNumber(number);
    }

    @Override
    public User createUser(String eMail, String phoneNumber, String password) {
        return new User(eMail, phoneNumber, password, accessLevelDAO.read(1));
    }

    @Override
    @Transactional
    public void createEntity(User user) throws DAOException {
        if (!isUserExists(user)) {
            userDAO.create(user);
        }
    }

    @Override
    @Transactional
    public User getEntityById(Integer id) throws DAOException {
        return userDAO.read(id);
    }

    @Override
    @Transactional
    public void updateEntity(User entity) throws DAOException {
        userDAO.update(entity);
    }

    @Override
    @Transactional
    public void deleteEntity(User entity) throws DAOException {
        userDAO.delete(entity);
    }

    @Override
    @Transactional
    public List<User> getAll() throws DAOException {
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public Map<User, Double> getTopTenUsers() throws UserNotFoundException {
        Map<User, Double> map = new HashMap<>();
        Map<User, Double> resultMap = new LinkedHashMap<>();

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
            resultMap.put(user, max);
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
