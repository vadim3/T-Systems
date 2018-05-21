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
public class OrderStatusDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int orderStatusId;

    private String status;
}
