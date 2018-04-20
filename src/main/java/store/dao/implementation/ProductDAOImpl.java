package store.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import store.dao.interfaces.ProductDAO;
import store.entities.Product;
import store.entities.ProductCategory;
import store.exceptions.ProductNotFoundException;

import javax.persistence.*;
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
    public List<Product> getAllProductByComplex(String categoryName, String vendorName, String minPrice, String maxPrice) throws ProductNotFoundException {

        try {
//            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
//            Root<Product> productRoot = cq.from(Product.class);
//            cq.where(cb.equal(productRoot.get("ProductCategory"), new ProductCategory(categoryName)));
//            cq.select(productRoot);
//            TypedQuery<Product> q = entityManager.createQuery(cq);
//
//            return q.getResultList();
            Double minArg = (minPrice == null || minPrice.isEmpty()) ? 0 : Double.parseDouble(minPrice);
            Double maxArg = (maxPrice == null || maxPrice.isEmpty()) ? Double.MAX_VALUE : Double.parseDouble(maxPrice);


            if ((vendorName == null || vendorName.isEmpty())  && (categoryName == null || categoryName.isEmpty())){
                Query query = entityManager.createQuery("select pr from Product pr where pr.price BETWEEN :minArg AND :maxArg")
                        .setParameter("minArg", minArg).setParameter("maxArg", maxArg);
                return (List<Product>) query.getResultList();
            } else if (categoryName == null || categoryName.isEmpty()){
                Query query = entityManager.createQuery("select pr from Product pr where (pr.ProductVendor.name=:vendor) AND (pr.price BETWEEN :minArg AND :maxArg)")
                        .setParameter("vendor", vendorName).setParameter("minArg", minArg).setParameter("maxArg", maxArg);
                return (List<Product>) query.getResultList();
            } else if (vendorName == null || vendorName.isEmpty()){
                Query query = entityManager.createQuery("select pr from Product pr where (pr.ProductCategory.name=:name) AND (pr.price BETWEEN :minArg AND :maxArg)")
                        .setParameter("name", categoryName).setParameter("minArg", minArg).setParameter("maxArg", maxArg);
                return (List<Product>) query.getResultList();
            }
            Query query = entityManager.createQuery("select pr from Product pr where (pr.ProductCategory.name=:name) AND (pr.ProductVendor.name=:vendor) AND (pr.price BETWEEN :minArg AND :maxArg)")
                    .setParameter("name", categoryName).setParameter("vendor", vendorName).setParameter("minArg", minArg).setParameter("maxArg", maxArg);
            return (List<Product>) query.getResultList();
        } catch (PersistenceException ex) {
            return null;
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
