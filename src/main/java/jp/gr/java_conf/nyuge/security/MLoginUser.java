package jp.gr.java_conf.nyuge.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class MLoginUser implements Serializable {

    @Id
    private String loginUserId;

    private String password;

    public MLoginUser() {
        // NOP
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}