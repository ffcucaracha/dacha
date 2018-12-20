package hello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "APP_ROLE", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name") })
public class AppRole {

    @Id
    @GeneratedValue
    @Column(name = "role_id", nullable = false)
    private int role_id;

    @Column(name = "role_name", length = 30, nullable = false)
    private String role_name;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int roleId) {
        this.role_id = roleId;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String roleName) {
        this.role_name = roleName;
    }

}