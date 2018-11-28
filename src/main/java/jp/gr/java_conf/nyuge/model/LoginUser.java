package jp.gr.java_conf.nyuge.model;

import javax.persistence.*;


@Entity
public class LoginUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean loginEnabled;

    public LoginUser(MLoginUser user){
        setId(0L);
        setLoginId(user.getLoginId());
        setPassword(user.getPassword());
        setRole("USER");
        setLoginEnabled(true);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isLoginEnabled() {
        return loginEnabled;
    }

    public void setLoginEnabled(boolean loginEnabled) {
        this.loginEnabled = loginEnabled;
    }
}