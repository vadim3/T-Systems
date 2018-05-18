package store.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Getter
@Setter
@ToString
public class PaymentMethodDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int paymentMethodId;

    private String status;

//    public int getPaymentMethodId() {
//        return paymentMethodId;
//    }
//
//    public void setPaymentMethodId(int paymentMethodId) {
//        this.paymentMethodId = paymentMethodId;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
}
