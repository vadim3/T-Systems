package store.dao.interfaces;



import store.entities.Product;
import store.entities.ProductCategory;
import store.exceptions.ProductNotFoundException;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface ProductDAO extends GenericDAO<Product, Integer>{
    public Product getProductByName(String name) throws ProductNotFoundException;

    public List<Product> getAllProductByComplex(String categoryName, String vendorName, String minPrice, String maxPrice, String page) throws ProductNotFoundException;

    public List<Product> getAllProductByCategory(String categoryName) throws ProductNotFoundException;

    public List<Product> getAllProductByVendor(String vendorName) throws ProductNotFoundException;

    public List<Product> getAllProductByPrice(String minPrice, String maxPrice) throws ProductNotFoundException;

    public int paginationPages(String categoryName, String vendorName, String minPrice, String maxPrice, String page) throws ProductNotFoundException;
}
