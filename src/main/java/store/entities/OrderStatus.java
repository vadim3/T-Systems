package store.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Entity
@Table(name = "`OrderStatus`")
@NamedQuery(name = "OrderStatus.getAll", query = "SELECT os FROM OrderStatus os")
@Getter
@Setter
@ToString
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
