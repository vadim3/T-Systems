package store.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int orderId;

    private UserDTO user;

    private PaymentMethodDTO paymentMethod;

    private ShippingMethodDTO shippingMethod;

    private OrderStatusDTO orderStatus;

    private Date dateTime;

    private Map<ProductDTO, Integer> products;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShippingMethodDTO getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethodDTO shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public OrderStatusDTO getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusDTO orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Map<ProductDTO, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductDTO, Integer> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
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
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(user, orderDTO.user) &&
                Objects.equals(dateTime, orderDTO.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, dateTime);
    }


}
