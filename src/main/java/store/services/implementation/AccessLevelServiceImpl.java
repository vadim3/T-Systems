package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.AccessLevelDAO;
import store.entities.AccessLevel;
import store.exceptions.DAOException;
import store.services.interfaces.AccessLevelService;

import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

/**
 * Access level operations
 */
@Service("accessLevel")
public class AccessLevelServiceImpl implements AccessLevelService {
    @Autowired
    private AccessLevelDAO accessLevelDAO;

    /**
     * Creating accessLevel entity in base
     *
     * @param accessLevel entity for creating
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void createEntity(AccessLevel accessLevel) throws DAOException {
        accessLevelDAO.create(accessLevel);
    }

    /**
     * Getting accessLevel entity by id
     *
     * @param id id for getting
     * @return level with adjusted id
     * @throws DAOException if connect with DAO goes wrong
     */

    @Override
    @Transactional
    public AccessLevel getEntityById(Integer id) throws DAOException {
        return accessLevelDAO.read(id);
    }

    /**
     * Updating accessLevel entity in base
     *
     * @param accessLevel entity for updating
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void updateEntity(AccessLevel accessLevel) throws DAOException {
        accessLevelDAO.update(accessLevel);
    }

    /**
     * Deleting accessLevel entity from base
     *
     * @param accessLevel entity for deleting
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void deleteEntity(AccessLevel accessLevel) throws DAOException {
        accessLevelDAO.delete(accessLevel);

    }

    /**
     * Getting list of access levels entities
     *
     * @return list of all levels
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public List<AccessLevel> getAll() throws DAOException {
        return accessLevelDAO.getAll();
    }

}
