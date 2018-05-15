package store.services.interfaces;


import store.dto.ProductCategoryDTO;


/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface ProductCategoryService extends GenericService<ProductCategoryDTO, Integer> {
    public ProductCategoryDTO getProductCategoryByName(String name);
}
