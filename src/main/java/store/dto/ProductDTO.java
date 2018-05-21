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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ProductDTO product = (ProductDTO) o;
//        return productId == product.productId &&
//                Double.compare(product.price, price) == 0 &&
//                stockQuantity == product.stockQuantity &&
//                Double.compare(product.weight, weight) == 0 &&
//                Double.compare(product.volume, volume) == 0 &&
//                Double.compare(product.power, power) == 0 &&
//                Objects.equals(name, product.name) &&
//                Objects.equals(ProductCategoryDTO, product.ProductCategoryDTO) &&
//                Objects.equals(ProductVendorDTO, product.ProductVendorDTO) &&
//                Objects.equals(color, product.color) &&
//                Objects.equals(description, product.description) &&
//                Objects.equals(imagePath, product.imagePath);
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        result = productId;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (color != null ? color.hashCode() : 0);
//        result = 31 * result + (description != null ? description.hashCode() : 0);
//        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
//        result = 31 * result + stockQuantity;
//        result = 31 * result + (ProductCategoryDTO != null ? ProductCategoryDTO.hashCode() : 0);
//        result = 31 * result + (ProductVendorDTO != null ? ProductVendorDTO.hashCode() : 0);
//        temp = Double.doubleToLongBits(price);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(weight);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(volume);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(power);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        return result;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return productId == that.productId &&
                stockQuantity == that.stockQuantity &&
                Double.compare(that.volume, volume) == 0 &&
                Double.compare(that.power, power) == 0 &&
                Double.compare(that.weight, weight) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(description, that.description) &&
                Objects.equals(color, that.color) &&
                Objects.equals(imagePath, that.imagePath) &&
                Objects.equals(ProductVendorDTO, that.ProductVendorDTO) &&
                Objects.equals(ProductCategoryDTO, that.ProductCategoryDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, price, stockQuantity, description, color, volume, power, imagePath, weight, ProductVendorDTO, ProductCategoryDTO);
    }
}
