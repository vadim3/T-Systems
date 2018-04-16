package store.entities;

import javax.persistence.*;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Entity
@Table(name = "ProductCategory")
@NamedQuery(name = "ProductCategory.getAll", query = "SELECT pc FROM ProductCategory pc")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productcategory_id")
    private int productCategoryId;

    @Basic
    @Column(name = "name")
    private String name;

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

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

        ProductCategory that = (ProductCategory) o;
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
