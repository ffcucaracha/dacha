package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APP_USER")
public class AppUser {
    @Id
    @Column(name="user_id")
    private int user_id;

    @Column(name="user_name")
    private String user_name;

    @Column(name = "encryted_password")
    private String encrypted_password;

    @Column(name = "enabled")
    private boolean enabled;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String userName) {
        this.user_name = userName;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encryptedPassword) {
        this.encrypted_password = encryptedPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString(){
        return "id="+user_id+", имя пользователя="+user_name;
    }


}