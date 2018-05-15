package store.services.interfaces;

import store.dto.ProductVendorDTO;


/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface ProductVendorService extends GenericService<ProductVendorDTO, Integer> {
    public ProductVendorDTO getProductVendorByName(String name);
}
