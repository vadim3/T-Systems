package store.dao.implementation;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("AccessLevel")
public class AccessLevelDAOImpl {
    @PersistenceContext
    private EntityManager entityManager;
}
