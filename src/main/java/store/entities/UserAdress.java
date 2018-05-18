package store.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Entity
@Table(name = "`UserAddress`")
@NamedQuery(name = "UserAdress.getAll", query = "SELECT ua FROM UserAdress ua")
@Getter
@Setter
@ToString
public class UserAdress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adress_id")
    private int adressId;

    @Basic
    @Column(name = "country")
    @NotNull
    private String country;

    @Basic
    @Column(name = "city")
    @NotNull
    private String city;

    @Basic
    @Column(name = "zipCode")
    @NotNull
    private String zipCode;

    @Basic
    @Column(name = "street")
    @NotNull
    private String street;

    @Basic
    @Column(name = "home")
    @NotNull
    private String home;

    @Basic
    @Column(name = "room")
    @NotNull
    private String room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAdress userAdress = (UserAdress) o;

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
