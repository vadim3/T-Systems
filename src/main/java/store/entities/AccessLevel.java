package store.entities;

import javax.persistence.*;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Entity
@Table(name = "AccessLevel")
@NamedQuery(name = "AccessLevel.getAll", query = "SELECT a FROM AccessLevel a")
public class AccessLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accessLevel_id")
    private int accessLevelId;

    @Basic
    @Column(name = "status")
    private String status;

    public AccessLevel(String status) {
        this.status = status;
    }

    public AccessLevel() {
    }

    public int getAccessLevelId() {
        return accessLevelId;
    }

    public void setAccessLevelId(int accessLevelId) {
        this.accessLevelId = accessLevelId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccessLevel{" +
                "accessLevelId=" + accessLevelId +
                ", status='" + status + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessLevel that = (AccessLevel) o;
        if (accessLevelId != that.accessLevelId) return false;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accessLevelId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

}
