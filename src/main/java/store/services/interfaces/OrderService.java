package store.services.interfaces;


import store.entities.Order;
import store.entities.OrderStatus;
import store.entities.PaymentMethod;
import store.entities.ShippingMethod;
import store.exceptions.OrderNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface OrderService extends GenericService<Order, Integer> {

    public List<Order> getAllOrdersByUser(int id);

    public Map<Order, Integer> getAllOrdersByUserMap(int id) throws OrderNotFoundException;

    public List<ShippingMethod> getAllShippingMethods();

    public ShippingMethod getShippingMethodByStatus(String status);

    public List<PaymentMethod> getAllPaymentMethods();

    public PaymentMethod getPaymentMethodByStatus(String status);

    public List<OrderStatus> getAllOrderStatuses();

    public OrderStatus getOrderStatusByStatus(String status);
}
