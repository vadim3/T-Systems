package store.services.interfaces;

import store.entities.Product;
import store.services.interfaces.GenericService;

import java.util.List;

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

    public List<Product> getProductByCategory(String name);

    public List<Product> getProductByVendor(String name);
}
