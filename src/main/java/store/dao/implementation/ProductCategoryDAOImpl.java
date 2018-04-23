package store.dao.implementation;


import org.springframework.stereotype.Repository;
import store.dao.interfaces.ProductCategoryDAO;
import store.dao.interfaces.ProductVendorDAO;
import store.entities.Product;
import store.entities.ProductCategory;
import store.entities.ProductVendor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("ProductCategory")
public class ProductCategoryDAOImpl extends GenericDAOImpl<ProductCategory, Integer> implements ProductCategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductCategory getProductCategoryByName(String name) {
        try {
            Query query = entityManager.createQuery("select pr from ProductCategory pr where pr.name=:name")
                    .setParameter("name", name);
            return (ProductCategory) query.getSingleResult();
        } catch (PersistenceException ex) {
            return null;
        }
    }
}
