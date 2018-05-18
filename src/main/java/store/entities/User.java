package store.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Entity
@Table(name = "`User`")
@NamedQuery(name = "User.getAll", query = "SELECT u FROM User u")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Basic
    @Column(name = "firstName")
    private String firstName;

    @Basic
    @Column(name = "secondName")
    private String secondName;

    @Basic
    @Column(name = "birthdayData")
    private String birthdayData;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accessLevel_id")
    private AccessLevel accessLevel;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id")
    private UserAdress userAdress;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Order> orders;

    public User() {
    }

    public User(String firstName, String secondName, String birthdayData, String email, String password, AccessLevel accessLevel, UserAdress userAdress) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthdayData = birthdayData;
        this.email = email;
        this.password = password;
        this.accessLevel = accessLevel;
        this.userAdress = userAdress;
    }

    public User(String email, String phoneNumber, String password, AccessLevel accessLevel) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null) return false;
        if (birthdayData != null ? !birthdayData.equals(user.birthdayData) : user.birthdayData != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (accessLevel != null ? !accessLevel.equals(user.accessLevel) : user.accessLevel != null) return false;
        if (userAdress != null ? !userAdress.equals(user.userAdress) : user.userAdress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (birthdayData != null ? birthdayData.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (accessLevel != null ? accessLevel.hashCode() : 0);
        result = 31 * result + (userAdress != null ? userAdress.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
