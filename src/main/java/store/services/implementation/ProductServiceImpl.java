package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.ProductDAO;
import store.entities.Order;
import store.entities.Product;
import store.exceptions.DAOException;
import store.exceptions.OrderNotFoundException;
import store.exceptions.ProductNotFoundException;
import store.services.interfaces.OrderService;
import store.services.interfaces.ProductCategoryService;
import store.services.interfaces.ProductService;
import store.services.interfaces.ProductVendorService;

import java.util.*;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductVendorService productVendorService;

    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public Product getProductByName(String name) {
        return productDAO.getProductByName(name);
    }

    @Override
    @Transactional
    public List<Product> getProductByComplex(String categoryName, String vendorName, String minPrice, String maxPrice) {
        return productDAO.getAllProductByComplex(categoryName, vendorName, minPrice, maxPrice);
    }

    @Override
    @Transactional
    public List<Product> getProductByCategory(String name) {
        return productDAO.getAllProductByCategory(name);
    }

    @Override
    @Transactional
    public List<Product> getProductByVendor(String name) {
        return productDAO.getAllProductByVendor(name);
    }

    @Override
    @Transactional
    public void updateAllFieldsProduct(String productId, String name, String price, String stockQuintity, String productCategory,
                                       String productVendor, String description, String imagePath, String weight, String volume,
                                       String color, String power) {
        Product product = getEntityById(Integer.parseInt(productId));
        product.setName(name);
        product.setPrice(Double.parseDouble(price));
        product.setStockQuantity(Integer.parseInt(stockQuintity));
        product.setProductCategory(productCategoryService.getProductCategoryByName(productCategory));
        product.setProductVendor(productVendorService.getProductVendorByName(productVendor));
        product.setDescription(description);
        product.setImagePath(imagePath);
        product.setWeight(Double.parseDouble(weight));
        product.setVolume(Double.parseDouble(volume));
        product.setColor(color);
        product.setPower(Double.parseDouble(power));
        updateEntity(product);
    }

    @Override
    @Transactional
    public void createNewProduct(String name, String price, String stockQuintity, String productCategory, String productVendor, String description, String imagePath, String weight, String volume, String color, String power) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(Double.parseDouble(price));
        product.setStockQuantity(Integer.parseInt(stockQuintity));
        product.setProductCategory(productCategoryService.getProductCategoryByName(productCategory));
        product.setProductVendor(productVendorService.getProductVendorByName(productVendor));
        product.setDescription(description);
        product.setImagePath(imagePath);
        product.setWeight(Double.parseDouble(weight));
        product.setVolume(Double.parseDouble(volume));
        product.setColor(color);
        product.setPower(Double.parseDouble(power));
        createEntity(product);
    }

    @Override
    @Transactional
    public Map<Product, Integer> getTenBestSellersProduct() throws OrderNotFoundException {
        Map<Product, Integer> map = new HashMap<>();
        Map<Product, Integer> resultMap = new LinkedHashMap<>();

        for (Order order : orderService.getAll()){
            for (Product product : order.getProducts()){
                if (map.containsKey(product)){
                    map.put(product, map.get(product)+1);
                } else {
                    map.put(product, 1);
                }
            }
        }
        Product productChoice = new Product();
        for (int i = 0; i < 10; i++){
            int max = 0;
            if (map.isEmpty()) break;
            for (Map.Entry<Product, Integer> entry : map.entrySet()){
                if (entry.getValue() > max){
                    max = entry.getValue();
                    productChoice = entry.getKey();
                }
            }
            resultMap.put(productChoice, max);
            map.remove(productChoice);
        }


        return resultMap;
    }

    @Override
    @Transactional
    public void createEntity(Product product) throws DAOException {
        productDAO.create(product);
    }

    @Override
    @Transactional
    public Product getEntityById(Integer id) throws DAOException {
        return productDAO.read(id);
    }

    @Override
    @Transactional
    public void updateEntity(Product product) throws DAOException {
        productDAO.update(product);
    }

    @Override
    @Transactional
    public void deleteEntity(Product product) throws DAOException {
        productDAO.delete(product);
    }

    @Override
    @Transactional
    public List<Product> getAll() throws DAOException {
        return productDAO.getAll();
    }
}
