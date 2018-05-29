package store.services.interfaces;


import store.dto.*;
import store.tools.SenderLocal;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface OrderService extends GenericService<OrderDTO, Integer> {

    public List<OrderDTO> getAllOrdersByUser(int id);

    public List<ShippingMethodDTO> getAllShippingMethods();

    public ShippingMethodDTO getShippingMethodByStatus(String status);

    public List<PaymentMethodDTO> getAllPaymentMethods();

    public PaymentMethodDTO getPaymentMethodByStatus(String status);

    public List<OrderStatusDTO> getAllOrderStatuses();

    public OrderStatusDTO getOrderStatusByStatus(String status);

    public void createOrder(UserDTO userDTO, String paymentMethod, String shippingMethod, Map<ProductDTO, Integer> products);

    public double getIncomeInPeriod(String from, String to) throws ParseException;

    public SenderLocal getSender();

    public void setSender(SenderLocal sender);
}
