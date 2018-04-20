package store.dao.implementation;

import org.springframework.stereotype.Repository;
import store.dao.interfaces.OrderDAO;
import store.entities.Order;
import store.entities.OrderStatus;
import store.entities.PaymentMethod;
import store.entities.ShippingMethod;
import store.exceptions.OrderNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Repository("Order")
public class OrderDAOImpl extends GenericDAOImpl<Order, Integer> implements OrderDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order getOrderById(int id) throws OrderNotFoundException {
        try {
            return (Order) entityManager.createQuery("select o from Order o where o.orderId=:id")
                    .setParameter("id", id).getSingleResult();
        } catch (PersistenceException e) {
            throw new OrderNotFoundException("Order " + id + " wasn't gotten", e);
        }
    }


    @Override
    public List<Order> getAllOrdersByUser(int id) throws OrderNotFoundException {
        try {
            Query query = entityManager.createQuery("select u.orders from User u where u.id=:id")
                    .setParameter("id", id);
            return (List<Order>) query.getResultList();
        } catch (PersistenceException ex) {
            throw new OrderNotFoundException("Orders for user " + id + " wasn't gotten", ex);
        }
    }

    @Override
    public List<ShippingMethod> getAllShippingMethods() {
        try {
            return (List<ShippingMethod>) entityManager.createQuery("select s from ShippingMethod s").getResultList();
        } catch (PersistenceException e) {
            throw new OrderNotFoundException("List isn't found", e);
        }
    }

    @Override
    public ShippingMethod getShippingMethodByStatus(String status) {
        try {
            Query query = entityManager.createQuery("select o.shippingMethod from Order o where o.shippingMethod=:status")
                    .setParameter("status", status);
            return (ShippingMethod) query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new OrderNotFoundException("Shipping method  " + status + " wasn't gotten", ex);
        }
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return (List<PaymentMethod>) entityManager.createQuery("select p from PaymentMethod p").getResultList();
    }

    @Override
    public PaymentMethod getPaymentMethodByStatus(String status) {
        try {
            Query query = entityManager.createQuery("select o.paymentMethod from Order o where o.paymentMethod=:status")
                    .setParameter("status", status);
            return (PaymentMethod) query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new OrderNotFoundException("Payment method  " + status + " wasn't gotten", ex);
        }
    }

    @Override
    public List<OrderStatus> getAllOrderStatuses() {
        return (List<OrderStatus>) entityManager.createQuery("select os from OrderStatus os").getResultList();
    }

    @Override
    public OrderStatus getOrderStatusByStatus(String status) {
        try {
            Query query = entityManager.createQuery("select o.orderStatus from Order o where o.orderStatus=:status")
                    .setParameter("status", status);
            return (OrderStatus) query.getSingleResult();
        } catch (PersistenceException ex) {
            throw new OrderNotFoundException("Order status  " + status + " wasn't gotten", ex);
        }
    }
}
