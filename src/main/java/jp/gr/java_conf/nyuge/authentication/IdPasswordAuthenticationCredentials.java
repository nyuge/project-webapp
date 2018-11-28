package jp.gr.java_conf.nyuge.security;

import java.io.Serializable;

public class IdPasswordAuthenticationCredentials implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password;

    public IdPasswordAuthenticationCredentials(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
