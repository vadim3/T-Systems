package store.dao.interfaces;

import org.springframework.transaction.annotation.Transactional;
import store.exceptions.DAOException;

import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface GenericDAO <E, K> {


    public void create(E entity) throws DAOException;


    public E read(K id) throws DAOException;


    public void update(E entity) throws DAOException;


    public void delete(E entity) throws DAOException;

    public List<E> getAll() throws DAOException;
}
