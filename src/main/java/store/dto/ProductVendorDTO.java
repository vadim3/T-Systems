package store.dto;

import java.io.Serializable;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class ProductVendorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productVendorId;

    private String name;

    public int getProductVendorId() {
        return productVendorId;
    }

    public void setProductVendorId(int productVendorId) {
        this.productVendorId = productVendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
