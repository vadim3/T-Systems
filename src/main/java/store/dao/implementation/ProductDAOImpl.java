package store.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import store.dao.interfaces.ProductCategoryDAO;
import store.dao.interfaces.ProductDAO;
import store.dao.interfaces.ProductVendorDAO;
import store.entities.Product;
import store.entities.ProductCategory;
import store.exceptions.ProductNotFoundException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("Product")
public class ProductDAOImpl extends GenericDAOImpl<Product, Integer> implements ProductDAO {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    private ProductVendorDAO productVendorDAO;

    int paginationPages = 8;

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
    public List<Product> getAllProductByComplex(String categoryName, String vendorName, String minPrice, String maxPrice, String page) throws ProductNotFoundException {

        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            Root<Product> c = criteriaQuery.from(Product.class);
            List<Predicate> predicates = new ArrayList<>();

            criteriaQuery.select(c);

            if (!(categoryName == null || categoryName.isEmpty())){
                predicates.add(builder.equal(c.get("ProductCategory"), productCategoryDAO.getProductCategoryByName(categoryName)));
            }
            if (!(vendorName == null || vendorName.isEmpty())){
                predicates.add(builder.equal(c.get("ProductVendor"), productVendorDAO.getProductVendorByName(vendorName)));
            }
            if (!(minPrice == null || minPrice.isEmpty())){
                predicates.add(builder.greaterThan(c.get("price"),Double.parseDouble(minPrice)));
            }
            if (!(maxPrice == null || maxPrice.isEmpty())){
                predicates.add(builder.lessThan(c.get("price"),Double.parseDouble(maxPrice)));
            }
            if (predicates.isEmpty()){
                criteriaQuery.select(c);
            } else {
                Predicate[] predicateArray = new Predicate[predicates.size()];
                for (int i = 0; i < predicates.size(); i++){
                    predicateArray[i] = predicates.get(i);
                }
                criteriaQuery.select(c).where(builder.and(predicateArray));
            }
            TypedQuery<Product> q = entityManager.createQuery(criteriaQuery);
            if (!(page == null || page.isEmpty())){
                return q.setFirstResult((Integer.valueOf(page)-1)*paginationPages).setMaxResults(paginationPages).getResultList();
            }
            return q.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    @Override
    public int paginationPages(String categoryName, String vendorName, String minPrice, String maxPrice, String page) throws ProductNotFoundException {
        try {

            return (getAllProductByComplex(categoryName, vendorName, minPrice, maxPrice, null).size() / paginationPages) + 1;
        } catch (PersistenceException ex) {
            return 0;
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
            Double minArg = (minPrice == null || minPrice.isEmpty()) ? 0 : Double.parseDouble(minPrice);
            Double maxArg = (maxPrice == null || maxPrice.isEmpty()) ? Double.MAX_VALUE : Double.parseDouble(maxPrice);
            Query query = entityManager.createQuery("select pr from Product pr where pr.price BETWEEN :minArg AND :maxArg")
                    .setParameter("minArg", minArg).setParameter("maxArg", maxArg);
            return (List<Product>) query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }
}
