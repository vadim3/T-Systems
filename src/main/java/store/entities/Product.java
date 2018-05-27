package store.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Component
@Scope("session")
@Entity
@Table(name = "`Product`")
@NamedQuery(name = "Product.getAll", query = "SELECT pr FROM Product pr")
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Basic
    @Column(name = "tradeName")
    private String name;

    @Basic
    @Column(name = "price")
    private double price;

    @Basic
    @Column(name = "stockQuantity")
    private int stockQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private ProductCategory ProductCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private ProductVendor ProductVendor;

    @Basic
    @Column(name = "weight")
    private Double weight;

    @Basic
    @Column(name = "volume")
    private Double volume;

    @Basic
    @Column(name = "color")
    private String color;

    @Basic
    @Column(name = "power")
    private Double power;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "image")
    private String imagePath;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products", cascade = CascadeType.MERGE)
    private List<Order> orders = new ArrayList<>();

    public Product() {
    }

    public Product(String name, double price, int stockQuantity, store.entities.ProductCategory productCategory,
                   store.entities.ProductVendor productVendor, double weight, double volume, String color,
                   double power, String description, String imagePath) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        ProductCategory = productCategory;
        ProductVendor = productVendor;
        this.weight = weight;
        this.volume = volume;
        this.color = color;
        this.power = power;
        this.description = description;
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                Double.compare(product.price, price) == 0 &&
                stockQuantity == product.stockQuantity &&
                Double.compare(product.weight, weight) == 0 &&
                Double.compare(product.volume, volume) == 0 &&
                Double.compare(product.power, power) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(ProductCategory, product.ProductCategory) &&
                Objects.equals(ProductVendor, product.ProductVendor) &&
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
        result = 31 * result + (ProductCategory != null ? ProductCategory.hashCode() : 0);
        result = 31 * result + (ProductVendor != null ? ProductVendor.hashCode() : 0);
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
