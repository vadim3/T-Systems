package store.dto;

import java.io.Serializable;

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
}
