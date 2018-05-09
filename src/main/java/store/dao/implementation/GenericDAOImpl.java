package store.dao.implementation;

import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.GenericDAO;
import store.exceptions.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

public abstract class GenericDAOImpl<E, K> implements GenericDAO<E, K> {
    protected Class<E> daoType;

    @PersistenceContext
    private EntityManager entityManager;

    public GenericDAOImpl() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public void create(E entity) throws DAOException {
        try {
            entityManager.persist(entity);
        } catch (PersistenceException e) {
            throw new DAOException("Entity wasn't created: " + entity, e);
        }
    }

    @Override
    public E read(K id) throws DAOException {
        try {
            return (E) this.entityManager.find(daoType, id);
        } catch (PersistenceException e) {
            throw new DAOException("Entity " + id + " wasn't found", e);
        }
    }

    @Override
    public void update(E entity) throws DAOException {
        try {
            entityManager.merge(entity);
        } catch (PersistenceException e) {
            throw new DAOException("Entity wasn't updated: " + entity, e);
        } catch (IllegalStateException e) {
            throw new DAOException("Entity wasn't updated: " + entity, e);
        }

    }

    @Override
    @Transactional
    public void delete(E entity) throws DAOException {
        try {
            entityManager.remove(entityManager.merge(entity));
        } catch (PersistenceException e) {
            throw new DAOException("Entity wasn't deleted: " + entity, e);
        }

    }

    @Override
    public List<E> getAll() throws DAOException {
        try {
            return entityManager.createNamedQuery(daoType.getSimpleName() + ".getAll", daoType).getResultList();
        } catch (PersistenceException ex) {
            throw new DAOException("Unable to get all entities of class " + daoType.getSimpleName(), ex);
        }
    }

}

