package store.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

    private List<ProductDTO> products;

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
