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
@Table(name = "`PaymentMethod`")
@NamedQuery(name = "PaymentMethod.getAll", query = "SELECT pm FROM PaymentMethod pm")
@Getter
@Setter
@ToString
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentMethod_id")
    private int paymentMethodId;

    @Basic
    @Column(name = "status")
    private String status;

    public PaymentMethod() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentMethod that = (PaymentMethod) o;
        if (paymentMethodId != that.paymentMethodId) return false;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paymentMethodId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
