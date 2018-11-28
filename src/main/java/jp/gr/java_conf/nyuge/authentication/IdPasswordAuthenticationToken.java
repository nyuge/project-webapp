package jp.gr.java_conf.nyuge.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class IdPasswordAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String loginId;

    private IdPasswordAuthenticationCredentials credentials;

    public IdPasswordAuthenticationToken(String loginId, IdPasswordAuthenticationCredentials credentials) {
        super(null);
        this.loginId = loginId;
        this.credentials = credentials;
    }

    @Override
    public String getPrincipal() {
        return loginId;
    }

    public IdPasswordAuthenticationCredentials getCredentials() {
        return credentials;
    }
}
