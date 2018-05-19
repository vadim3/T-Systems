package store.dao.implementation;

import org.springframework.stereotype.Repository;
import store.dao.interfaces.AccessLevelDAO;
import store.entities.AccessLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("AccessLevel")
public class AccessLevelDAOImpl extends GenericDAOImpl<AccessLevel, Integer> implements AccessLevelDAO {
    @PersistenceContext
    private EntityManager entityManager;


}
