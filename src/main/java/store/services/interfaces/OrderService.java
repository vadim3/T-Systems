package store.services.interfaces;


import store.entities.*;
import store.exceptions.OrderNotFoundException;

import java.text.ParseException;
import java.util.Date;
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

    public Map<Product, Integer> transformListToMap(List<Product> orders);

    public List<Product> transformMapToList(Map<Product, Integer> orders);

    public void createOrder(User user, String paymentMethod, String shippingMethod, Map<Product, Integer> orders);

    public double getIncomeInPeriod(String from, String to) throws ParseException;
}
