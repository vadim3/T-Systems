package store.dto;

import java.io.Serializable;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class UserAdressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int adressId;

    private String country;

    private String city;

    private String zipCode;

    private String street;

    private String home;

    private String room;

    public int getAdressId() {
        return adressId;
    }

    public void setAdressId(int adressId) {
        this.adressId = adressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

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

    @Override
    public String toString() {
        return "UserAdressDTO{" +
                "adressId=" + adressId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", home='" + home + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
