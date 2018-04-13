package store.dao.interfaces;

import org.springframework.stereotype.Repository;
import store.entities.AccessLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

public interface AccessLevelDAO extends GenericDAO<AccessLevel, Integer>{

}
