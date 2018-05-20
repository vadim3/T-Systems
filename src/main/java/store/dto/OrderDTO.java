package store.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Getter
@Setter
@ToString
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int orderId;

    private PaymentMethodDTO paymentMethod;

    private ShippingMethodDTO shippingMethod;

    private OrderStatusDTO orderStatus;

    private Date dateTime;

    private Map<ProductDTO, Integer> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return orderId == orderDTO.orderId &&
                Objects.equals(dateTime, orderDTO.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, dateTime);
    }
}
