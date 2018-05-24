package store.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import store.entities.UserAdress;

import javax.validation.constraints.*;
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

    @Size(min = 1, message = "Please enter the firstName")
    private String firstName;

    @Size(min = 1, message = "Please enter the secondName")
    private String secondName;

//    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$",
//            message = "Please enter the Birthday dd/mm/yyyy")
    private String birthdayData;

    @Pattern(regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            flags={Pattern.Flag.CASE_INSENSITIVE} ,message="Please enter the Email")
    private String email;

    @Pattern(regexp="^79[0-9]{9}$", message="Phone Number must be in right format 79XXXXXXXXX")
    private String phoneNumber;

    private String accessLevel;

}
