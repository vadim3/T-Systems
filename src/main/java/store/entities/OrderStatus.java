package store.entities;

import javax.persistence.*;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Entity
@Table(name = "OrderStatus")
@NamedQuery(name = "OrderStatus.getAll", query = "SELECT os FROM OrderStatus os")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderStatus_id")
    private int orderStatusId;

    @Basic
    @Column(name = "status")
    private String status;

    public OrderStatus() {
    }

    public OrderStatus(String status) {
        this.status = status;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "orderStatusId=" + orderStatusId +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatus that = (OrderStatus) o;
        if (orderStatusId != that.orderStatusId) return false;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderStatusId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
