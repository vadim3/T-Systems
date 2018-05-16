package store.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Entity
@Table(name = "`ProductVendor`")
@NamedQuery(name = "ProductVendor.getAll", query = "SELECT p FROM ProductVendor p")
public class ProductVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productVendor_id")
    private int productVendorId;

    @Basic
    @Column(name = "name")
    private String name;

    public ProductVendor() {
    }

    public ProductVendor(String name) {
        this.name = name;
    }

    public int getProductVendorId() {
        return productVendorId;
    }

    public void setProductVendorId(int productVendor_id) {
        this.productVendorId = productVendor_id;
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

        ProductVendor that = (ProductVendor) o;
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
}
