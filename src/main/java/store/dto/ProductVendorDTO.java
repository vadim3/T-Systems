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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVendorDTO that = (ProductVendorDTO) o;
        if (productVendorId != that.productVendorId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = productVendorId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductVendorDTO{" +
                "productVendorId=" + productVendorId +
                ", name='" + name + '\'' +
                '}';
    }
}
