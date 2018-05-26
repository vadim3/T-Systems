package store.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Getter
@Setter
@ToString
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productId;
    @Size(min = 1, message = "Please enter Name")
    private String name;
    @Min(0)
    private Double price;
    @Min(0)
    private int stockQuantity;
    @Size(min = 1, message = "Please enter Description")
    private String description;

    private String color;

    private double volume;

    private double power;

    private String imagePath;

    private double weight;

    private ProductVendorDTO ProductVendorDTO;

    private ProductCategoryDTO ProductCategoryDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return productId == that.productId &&
                Objects.equals(name, that.name) &&
                Objects.equals(ProductVendorDTO, that.ProductVendorDTO) &&
                Objects.equals(ProductCategoryDTO, that.ProductCategoryDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, name, ProductVendorDTO, ProductCategoryDTO);
    }
}
