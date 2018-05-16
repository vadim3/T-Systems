package store.dto;

import store.entities.UserAdress;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String firstName;

    private String secondName;

    private String birthdayData;

    private String email;

    private String phoneNumber;

    private String AccessLevel;

    private UserAdressDTO userAdressDTO;

    private List<OrderDTO> ordersDTO;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getBirthdayData() {
        return birthdayData;
    }

    public void setBirthdayData(String birthdayData) {
        this.birthdayData = birthdayData;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccessLevel() {
        return AccessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        AccessLevel = accessLevel;
    }

    public UserAdressDTO getUserAdressDTO() {
        return userAdressDTO;
    }

    public void setUserAdressDTO(UserAdressDTO userAdressDTO) {
        this.userAdressDTO = userAdressDTO;
    }

    public List<OrderDTO> getOrdersDTO() {
        return ordersDTO;
    }

    public void setOrdersDTO(List<OrderDTO> ordersDTO) {
        this.ordersDTO = ordersDTO;
    }


}
