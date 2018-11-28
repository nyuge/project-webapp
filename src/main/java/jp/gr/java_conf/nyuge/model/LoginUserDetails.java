package jp.gr.java_conf.nyuge.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUserDetails implements UserDetails {

    private static final long serialVersionUID = -256740067874995659L;

    private LoginUser user;

    private Collection<GrantedAuthority> authorities;

    protected LoginUserDetails() {
        // NOP
    }

    public LoginUserDetails(LoginUser user, Collection<GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        // Basic認証はusernameとpasswordで試行されるため、メソッド名とメンバ変数の齟齬が発生する
        return user.getLoginId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isLoginEnabled();
    }

}