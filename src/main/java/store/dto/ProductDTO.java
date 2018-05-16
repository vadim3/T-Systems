package store.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public store.dto.ProductVendorDTO getProductVendorDTO() {
        return ProductVendorDTO;
    }

    public void setProductVendorDTO(store.dto.ProductVendorDTO productVendorDTO) {
        ProductVendorDTO = productVendorDTO;
    }

    public store.dto.ProductCategoryDTO getProductCategoryDTO() {
        return ProductCategoryDTO;
    }

    public void setProductCategoryDTO(store.dto.ProductCategoryDTO productCategoryDTO) {
        ProductCategoryDTO = productCategoryDTO;
    }

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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", volume=" + volume +
                ", power=" + power +
                ", imagePath='" + imagePath + '\'' +
                ", weight=" + weight +
                ", ProductVendorDTO=" + ProductVendorDTO +
                ", ProductCategoryDTO=" + ProductCategoryDTO +
                '}';
    }
}
