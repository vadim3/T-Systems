package store.services.interfaces;

import store.entities.Product;
import store.exceptions.OrderNotFoundException;


import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface ProductService extends GenericService<Product, Integer> {
    /**
     * Getting product entity by title
     *
     * @param name entity for getting
     * @return tariff entity
     */
    public Product getProductByName(String name);

    public List<Product> getProductByComplex(String categoryName, String vendorName, String minPrice, String maxPrice);

    public List<Product> getProductByCategory(String name);

    public List<Product> getProductByVendor(String name);

    public void updateAllFieldsProduct(String productId, String name, String price, String stockQuintity,
                                       String productCategory, String productVendor, String description,
                                       String imagePath, String weight, String volume, String color, String power);

    public void createNewProduct(String name, String price, String stockQuintity,
                                 String productCategory, String productVendor, String description,
                                 String imagePath, String weight, String volume, String color, String power);

    public Map<Product, Integer> getTenBestSellersProduct() throws OrderNotFoundException;
}
