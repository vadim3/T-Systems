package store.dao.implementation;


import org.springframework.stereotype.Repository;
import store.dao.interfaces.ProductVendorDAO;
import store.entities.ProductVendor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import store.exceptions.ProductVendorNotFoundException;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("ProductVendor")
public class ProductVendorDAOImpl extends GenericDAOImpl<ProductVendor, Integer> implements ProductVendorDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductVendor getProductVendorByName(String name) {
        try {
            Query query = entityManager.createQuery("select pv from ProductVendor pv where pv.name=:name")
                    .setParameter("name", name);
            return (ProductVendor) query.getSingleResult();
        } catch (PersistenceException ex) {
            return null;
        }
    }
}
