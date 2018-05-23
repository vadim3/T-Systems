package store.services.interfaces;

import store.dto.ProductDTO;
import store.entities.Product;
import store.exceptions.OrderNotFoundException;


import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface ProductService extends GenericService<ProductDTO, Integer> {
    /**
     * Getting product entity by title
     *
     * @param name entity for getting
     * @return tariff entity
     */
    public ProductDTO getProductByName(String name);

    public List<ProductDTO> getProductByComplex(String categoryName, String vendorName, String minPrice, String maxPrice, String page);

    public List<ProductDTO> getProductByCategory(String name);

    public List<ProductDTO> getProductByVendor(String name);

    public Map<ProductDTO, Integer> getTenBestSellersProduct() throws OrderNotFoundException;

    List<ProductDTO> getTenBestSellersProductList();

    public int paginationPages(String categoryName, String vendorName, String minPrice, String maxPrice, String page);

    public int itemsQuintity(String categoryName, String vendorName, String minPrice, String maxPrice, String page);
}
