package store.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import store.entities.UserAdress;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String firstName;

    private String secondName;

    private String birthdayData;

    @Email
    private String email;

    @Size(min = 5, message = "Phone Number length must be more 5 digits")
    private String phoneNumber;

    @NotNull
    private String accessLevel;

    private UserAdressDTO userAdressDTO;

    private List<OrderDTO> ordersDTO;

}
