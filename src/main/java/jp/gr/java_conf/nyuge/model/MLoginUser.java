package jp.gr.java_conf.nyuge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class MLoginUser implements Serializable {

    @Id
    private String loginId;

    private String password;

    public MLoginUser() {
        // NOP
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}