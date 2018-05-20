package store.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Getter
@Setter
@ToString
public class ProductVendorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productVendorId;

    @Size(min = 1, message = "Please enter the Vendor Name")
    private String name;

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

}
