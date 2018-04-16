package store.services.interfaces;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

import store.exceptions.DAOException;

import java.util.List;

/**
 * Interface for GenericService
 */
public interface GenericService<E, K> {
    public void createEntity(E entity) throws DAOException;

    public E getEntityById(K id) throws DAOException;

    public void updateEntity(E entity) throws DAOException;

    public void deleteEntity(E entity) throws DAOException;

    public List<E> getAll() throws DAOException;


}
