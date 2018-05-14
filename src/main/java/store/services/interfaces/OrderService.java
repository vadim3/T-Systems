package store.services.interfaces;


import store.dto.*;
import store.entities.*;
import store.exceptions.OrderNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface OrderService extends GenericService<OrderDTO, Integer> {

    public List<OrderDTO> getAllOrdersByUser(int id);

    public Map<OrderDTO, Integer> getAllOrdersByUserMap(int id) throws OrderNotFoundException;

    public List<ShippingMethodDTO> getAllShippingMethods();

    public ShippingMethod getShippingMethodByStatus(String status);

    public List<PaymentMethodDTO> getAllPaymentMethods();

    public PaymentMethod getPaymentMethodByStatus(String status);

    public List<OrderStatusDTO> getAllOrderStatuses();

    public OrderStatus getOrderStatusByStatus(String status);

    public void createOrder(User user, String paymentMethod, String shippingMethod, Map<ProductDTO, Integer> orders);

    public double getIncomeInPeriod(String from, String to) throws ParseException;
}
