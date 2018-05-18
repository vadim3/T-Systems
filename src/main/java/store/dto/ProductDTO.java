package store.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    private String name;

    private double price;

    private int stockQuantity;

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
        ProductDTO product = (ProductDTO) o;
        return productId == product.productId &&
                Double.compare(product.price, price) == 0 &&
                stockQuantity == product.stockQuantity &&
                Double.compare(product.weight, weight) == 0 &&
                Double.compare(product.volume, volume) == 0 &&
                Double.compare(product.power, power) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(ProductCategoryDTO, product.ProductCategoryDTO) &&
                Objects.equals(ProductVendorDTO, product.ProductVendorDTO) &&
                Objects.equals(color, product.color) &&
                Objects.equals(description, product.description) &&
                Objects.equals(imagePath, product.imagePath);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = productId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + stockQuantity;
        result = 31 * result + (ProductCategoryDTO != null ? ProductCategoryDTO.hashCode() : 0);
        result = 31 * result + (ProductVendorDTO != null ? ProductVendorDTO.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(power);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
