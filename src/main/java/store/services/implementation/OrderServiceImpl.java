package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.OrderDAO;
import store.dao.interfaces.ProductDAO;
import store.dao.interfaces.UserDAO;
import store.dto.*;
import store.entities.*;
import store.exceptions.DAOException;
import store.exceptions.OrderNotFoundException;
import store.services.interfaces.EntityDTOMapper;
import store.services.interfaces.OrderService;
import store.services.interfaces.ProductService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private EntityDTOMapper entityDTOMapper;

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void createEntity(OrderDTO orderDTO) throws DAOException {
        orderDAO.create(entityDTOMapper.mapOrderFromDTO(orderDTO));
    }

    @Override
    @Transactional
    public OrderDTO getEntityById(Integer id) throws DAOException {
        return entityDTOMapper.mapDTOFromOrder(orderDAO.read(id));
    }

    @Override
    @Transactional
    public void updateEntity(OrderDTO orderDTO) throws DAOException {
        orderDAO.update(entityDTOMapper.mapOrderFromDTO(orderDTO));
    }

    @Override
    @Transactional
    public void deleteEntity(OrderDTO orderDTO) throws DAOException {
        orderDAO.delete(entityDTOMapper.mapOrderFromDTO(orderDTO));
    }

    @Override
    @Transactional
    public List<OrderDTO> getAll() throws DAOException {
        List<Order> orderList = orderDAO.getAll();
        return orderList.stream().map(order -> entityDTOMapper.mapDTOFromOrder(order)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllOrdersByUser(int id) {
        List<Order> orderList = orderDAO.getAllOrdersByUser(id);
        return orderList.stream().map(order -> entityDTOMapper.mapDTOFromOrder(order)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ShippingMethodDTO> getAllShippingMethods() {
        List<ShippingMethod> shippingMethodList = orderDAO.getAllShippingMethods();
        return shippingMethodList.stream().map(shippingMethod -> entityDTOMapper.mapDTOFromShippingMethod(shippingMethod)).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public ShippingMethodDTO getShippingMethodByStatus(String status) {
        return entityDTOMapper.mapDTOFromShippingMethod(orderDAO.getShippingMethodByStatus(status));
    }

    @Override
    @Transactional
    public List<PaymentMethodDTO> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethodList = orderDAO.getAllPaymentMethods();
        return paymentMethodList.stream().map(paymentMethod -> entityDTOMapper.mapDTOFromPaymentMethod(paymentMethod)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaymentMethodDTO getPaymentMethodByStatus(String status) {
        return entityDTOMapper.mapDTOFromPaymentMethod(orderDAO.getPaymentMethodByStatus(status));
    }

    @Override
    @Transactional
    public List<OrderStatusDTO> getAllOrderStatuses() {
        List<OrderStatus> orderStatusList = orderDAO.getAllOrderStatuses();
        return orderStatusList.stream().map(orderStatus -> entityDTOMapper.mapDTOFromOrderStatus(orderStatus)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderStatusDTO getOrderStatusByStatus(String status) {
        return entityDTOMapper.mapDTOFromOrderStatus(orderDAO.getOrderStatusByStatus(status));
    }

    @Override
    @Transactional
    public void createOrder(UserDTO userDTO, String paymentMethod, String shippingMethod, Map<ProductDTO, Integer> products) {

        User user = userDAO.read(Integer.valueOf(userDTO.getUserId()));
        entityDTOMapper.mapUserFromDTO(user, userDTO);
        PaymentMethod paymentMethod1 = entityDTOMapper.mapPaymentMethodFromDTO(getPaymentMethodByStatus(paymentMethod));
        ShippingMethod shippingMethod1 = entityDTOMapper.mapShippingMethodFromDTO(getShippingMethodByStatus(shippingMethod));
        OrderStatus orderStatus = entityDTOMapper.mapOrderStatusFromDTO(getOrderStatusByStatus("Paid"));
        List<Product> productList = entityDTOMapper.transformMapToList(products);
        Order order = new Order(user, paymentMethod1, shippingMethod1, orderStatus, new Date(), productList);
        orderDAO.create(order);

        for (Map.Entry<ProductDTO, Integer> entry : products.entrySet())
        {
            Product product = productDAO.read(entry.getKey().getProductId());
            product.setStockQuantity(product.getStockQuantity() - entry.getValue());
            productDAO.update(product);
        }

    }

    @Override
    @Transactional
    public double getIncomeInPeriod(String from, String to) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dateFrom = formatter.parse(from);
            Date dateTo = formatter.parse(to);
            List<Order> orders = orderDAO.getAll();
            double sum = 0;
            for (Order order : orders){
                if (order.getDateTime().getTime() > dateFrom.getTime() && order.getDateTime().getTime() < dateTo.getTime()){
                    for (Product product : order.getProducts()){
                        sum += product.getPrice();
                    }
                }
            }
            return sum;
    }



}
