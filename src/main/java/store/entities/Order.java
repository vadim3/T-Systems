package store.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Entity
@Table(name = "`Order`")
@NamedQuery(name = "Order.getAll", query = "SELECT ord FROM Order ord")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shippingMethod_id")
    private ShippingMethod shippingMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderStatus_id")
    private OrderStatus orderStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "OrderToProduct",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList();

    public Order() {
    }

    public Order(User user, PaymentMethod paymentMethod, ShippingMethod shippingMethod, OrderStatus orderStatus,
                 Date dateTime, List<Product> products) {
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.shippingMethod = shippingMethod;
        this.orderStatus = orderStatus;
        this.dateTime = dateTime;
        this.products = products;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", paymentMethod=" + paymentMethod +
                ", shippingMethod=" + shippingMethod +
                ", orderStatus=" + orderStatus +
                ", dateTime=" + dateTime +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                Objects.equals(user, order.user) &&
                Objects.equals(paymentMethod, order.paymentMethod) &&
                Objects.equals(shippingMethod, order.shippingMethod) &&
                Objects.equals(orderStatus, order.orderStatus) &&
                Objects.equals(dateTime, order.dateTime) &&
                Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (shippingMethod != null ? shippingMethod.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }
}
