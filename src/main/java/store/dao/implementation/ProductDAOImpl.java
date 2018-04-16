package store.dao.implementation;

import org.springframework.stereotype.Repository;
import store.dao.interfaces.ProductDAO;
import store.entities.Product;
import store.exceptions.ProductNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("Product")
public class ProductDAOImpl extends GenericDAOImpl<Product, Integer> implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product getProductByName(String name) throws ProductNotFoundException {
        try {
            return (Product) entityManager.createQuery("select pr from Product pr where pr.name=:name")
                    .setParameter("name", name).getSingleResult();
        } catch (PersistenceException e) {
            throw new ProductNotFoundException("Product " + name + " wasn't gotten", e);
        }
    }

    @Override
    public List<Product> getAllProductByCategory(String categoryName) throws ProductNotFoundException {
        try {
            Query query = entityManager.createQuery("select pr from Product pr where pr.ProductCategory.name=:name")
                    .setParameter("name", categoryName);
            return (List<Product>) query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    @Override
    public List<Product> getAllProductByVendor(String vendorName) throws ProductNotFoundException {
        try {
            Query query = entityManager.createQuery("select pr from Product pr where pr.ProductVendor.name=:name")
                    .setParameter("name", vendorName);
            return (List<Product>) query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    @Override
    public List<Product> getAllProductByPrice(String minPrice, String maxPrice) throws ProductNotFoundException {
        try {
            Double minArg = (minPrice.isEmpty() || minPrice.equals(null)) ? 0 : Double.parseDouble(minPrice);
            Double maxArg = (maxPrice.isEmpty() || maxPrice.equals(null)) ? Double.MAX_VALUE : Double.parseDouble(maxPrice);
            Query query = entityManager.createQuery("select pr from Product pr where pr.price BETWEEN :minArg AND :maxArg")
                    .setParameter("minArg", minArg).setParameter("maxArg", maxArg);
            return (List<Product>) query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }
}
