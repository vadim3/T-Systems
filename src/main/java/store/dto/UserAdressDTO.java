package store.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Getter
@Setter
@ToString
public class UserAdressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int adressId;
    @NotNull(message = "Please enter the country")
    private String country;

    @NotNull(message = "Please enter the city")
    private String city;

    @NotNull(message = "Please enter the zipCode")
    private String zipCode;

    @NotNull(message = "Please enter the street")
    private String street;

    @NotNull(message = "Please enter number or literal of home")
    private String home;

    @NotNull(message = "Please enter number of room")
    private String room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAdressDTO userAdress = (UserAdressDTO) o;

        if (country != null ? !country.equals(userAdress.country) : userAdress.country != null) return false;
        if (city != null ? !city.equals(userAdress.city) : userAdress.city != null) return false;
        if (zipCode != null ? !zipCode.equals(userAdress.zipCode) : userAdress.zipCode != null) return false;
        if (street != null ? !street.equals(userAdress.street) : userAdress.street != null) return false;
        if (home != null ? !home.equals(userAdress.home) : userAdress.home != null) return false;
        if (room != null ? !room.equals(userAdress.room) : userAdress.room != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adressId;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }
}
