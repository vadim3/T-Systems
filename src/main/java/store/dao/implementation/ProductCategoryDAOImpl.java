package store.dao.implementation;


import org.springframework.stereotype.Repository;
import store.dao.interfaces.ProductCategoryDAO;
import store.dao.interfaces.ProductVendorDAO;
import store.entities.ProductCategory;
import store.entities.ProductVendor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("ProductCategory")
public class ProductCategoryDAOImpl extends GenericDAOImpl<ProductCategory, Integer> implements ProductCategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

}
