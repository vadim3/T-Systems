package store.entities;

import javax.persistence.*;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Entity
@Table(name = "`ShippingMethod`")
@NamedQuery(name = "ShippingMethod.getAll", query = "SELECT shm FROM ShippingMethod shm")
public class ShippingMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shippingMethod_id")
    private int shippingMethodId;

    @Basic
    @Column(name = "status")
    private String status;

    public ShippingMethod() {
    }

    public ShippingMethod(String status) {
        this.status = status;
    }

    public int getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(int shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShippingMethod{" +
                "shippingMethodId=" + shippingMethodId +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShippingMethod that = (ShippingMethod) o;
        if (shippingMethodId != that.shippingMethodId) return false;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shippingMethodId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
