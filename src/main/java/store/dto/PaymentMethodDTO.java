package store.dto;


import java.io.Serializable;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

public class PaymentMethodDTO implements Serializable {

    private int paymentMethodId;

    private String status;

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
