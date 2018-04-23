package store.dao.interfaces;

import org.springframework.stereotype.Repository;
import store.entities.AccessLevel;
import store.exceptions.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

public interface AccessLevelDAO extends GenericDAO<AccessLevel, Integer>{
    /**
     * Getting user Access Level entity by status
     *
     * @param number entity for getting
     * @return user with adjusted number
     */
//    public AccessLevel getUserByNumber(String number) throws DAOException;
}
