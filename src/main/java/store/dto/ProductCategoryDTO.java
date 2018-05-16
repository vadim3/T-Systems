package store.dto;

import java.io.Serializable;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class ProductCategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productCategoryId;

    private String name;

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
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

        ProductCategoryDTO that = (ProductCategoryDTO) o;
        if (productCategoryId != that.productCategoryId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productCategoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
