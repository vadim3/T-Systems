package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.OrderDAO;
import store.entities.Order;
import store.entities.OrderStatus;
import store.entities.PaymentMethod;
import store.entities.ShippingMethod;
import store.exceptions.DAOException;
import store.exceptions.OrderNotFoundException;
import store.services.interfaces.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    @Transactional
    public void createEntity(Order order) throws DAOException {
        orderDAO.create(order);
    }

    @Override
    @Transactional
    public Order getEntityById(Integer id) throws DAOException {
        return orderDAO.read(id);
    }

    @Override
    @Transactional
    public void updateEntity(Order order) throws DAOException {
        orderDAO.update(order);
    }

    @Override
    @Transactional
    public void deleteEntity(Order order) throws DAOException {
        orderDAO.delete(order);
    }

    @Override
    @Transactional
    public List<Order> getAll() throws DAOException {
        return orderDAO.getAll();
    }

    @Override
    @Transactional
    public List<Order> getAllOrdersByUser(int id) {
        return orderDAO.getAllOrdersByUser(id);
    }

    @Override
    public Map<Order, Integer> getAllOrdersByUserMap(int id) throws OrderNotFoundException {
        Map<Order, Integer> orderMap = new HashMap<>();
        List<Order> orderList = getAllOrdersByUser(id);
        for (Order order: orderList) {
            if (orderMap.containsKey(order)){
                orderMap.put(order, orderMap.get(order) + 1);
            } else {
                orderMap.put(order, 1);
            }
        }
        return orderMap;
    }

    @Override
    @Transactional
    public List<ShippingMethod> getAllShippingMethods() {
        return orderDAO.getAllShippingMethods();
    }

    @Override
    @Transactional
    public ShippingMethod getShippingMethodByStatus(String status) {
        return orderDAO.getShippingMethodByStatus(status);
    }

    @Override
    @Transactional
    public List<PaymentMethod> getAllPaymentMethods() {
        return orderDAO.getAllPaymentMethods();
    }

    @Override
    @Transactional
    public PaymentMethod getPaymentMethodByStatus(String status) {
        return orderDAO.getPaymentMethodByStatus(status);
    }

    @Override
    @Transactional
    public List<OrderStatus> getAllOrderStatuses() {
        return orderDAO.getAllOrderStatuses();
    }

    @Override
    @Transactional
    public OrderStatus getOrderStatusByStatus(String status) {
        return orderDAO.getOrderStatusByStatus(status);
    }
}
