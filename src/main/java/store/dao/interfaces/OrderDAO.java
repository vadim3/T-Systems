package store.dao.interfaces;
import store.entities.Order;
import store.entities.OrderStatus;
import store.entities.PaymentMethod;
import store.entities.ShippingMethod;
import store.exceptions.OrderNotFoundException;

import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface OrderDAO extends GenericDAO<Order, Integer> {

    public Order getOrderById(int id) throws OrderNotFoundException;

    public List<Order> getAllOrdersByUser(int id) throws OrderNotFoundException;

    public List<ShippingMethod> getAllShippingMethods();

    public ShippingMethod getShippingMethodByStatus(String status);

    public List<PaymentMethod> getAllPaymentMethods();

    public PaymentMethod getPaymentMethodByStatus(String status);

    public List<OrderStatus> getAllOrderStatuses();

    public OrderStatus getOrderStatusByStatus(String status);

}
