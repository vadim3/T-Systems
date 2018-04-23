package store.dao.interfaces;

import store.entities.ProductCategory;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

public interface ProductCategoryDAO extends GenericDAO<ProductCategory, Integer> {

    ProductCategory getProductCategoryByName(String name);

}
