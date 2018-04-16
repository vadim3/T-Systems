package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.UserDAO;
import store.entities.User;
import store.exceptions.DAOException;
import store.exceptions.UserNotFoundException;
import store.services.interfaces.UserService;

import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

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

    public boolean isUserExists(User user) {
        try {
            return getUserByeMail(user.getEmail()) != null ? true : false;
        } catch (UserNotFoundException ex) {
            return false;
        }
    }
}
