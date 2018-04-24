package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.OrderDAO;
import store.entities.*;
import store.exceptions.DAOException;
import store.exceptions.OrderNotFoundException;
import store.services.interfaces.OrderService;
import store.services.interfaces.ProductService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductService productService;

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

    @Override
    @Transactional
    public Map<Product, Integer> transformListToMap(List<Product> orders) {
        Map<Product, Integer> productMap = new HashMap<>();

        for (Product product: orders) {
            if (productMap.containsKey(product)){
                productMap.put(product, productMap.get(product) + 1);
            } else {
                productMap.put(product, 1);
            }
        }

        return productMap;
    }

    @Override
    @Transactional
    public List<Product> transformMapToList(Map<Product, Integer> orders) {
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : orders.entrySet())
        {
            for (int i = 0; i < entry.getValue(); i++){
                productList.add(entry.getKey());
            }
        }

        return productList;
    }

    @Override
    @Transactional
    public void createOrder(User user, String paymentMethod, String shippingMethod, Map<Product, Integer> orders) {

        Order order = new Order(user,getPaymentMethodByStatus(paymentMethod), getShippingMethodByStatus(shippingMethod),
                getOrderStatusByStatus("Paid"), new Date(), transformMapToList(orders));

        createEntity(order);

        for (Map.Entry<Product, Integer> entry : orders.entrySet())
        {
            Product product = productService.getEntityById(entry.getKey().getProductId());
            product.setStockQuantity(product.getStockQuantity() - entry.getValue());
            productService.updateEntity(product);
        }

    }

    @Override
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
