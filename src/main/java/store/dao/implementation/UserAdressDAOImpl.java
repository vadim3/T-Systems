package store.dao.implementation;

import store.dao.interfaces.UserAdressDAO;
import store.entities.UserAdress;
import store.exceptions.DAOException;

import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class UserAdressDAOImpl implements UserAdressDAO {
    @Override
    public void create(UserAdress entity) throws DAOException {

    }

    @Override
    public UserAdress read(Integer id) throws DAOException {
        return null;
    }

    @Override
    public void update(UserAdress entity) throws DAOException {

    }

    @Override
    public void delete(UserAdress entity) throws DAOException {

    }

    @Override
    public List<UserAdress> getAll() throws DAOException {
        return null;
    }
}
