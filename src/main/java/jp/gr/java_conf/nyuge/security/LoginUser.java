package jp.gr.java_conf.nyuge.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


/**
 * 認証ユーザーの情報を格納するクラス
 */
public class LoginUser extends User {

    /**
     * ログインユーザー
     */
    private final MLoginUser mLoginUser;

    public LoginUser(MLoginUser user) {
        // スーパークラスのユーザーID、パスワードに値をセットする
        // 実際の認証はスーパークラスのユーザーID、パスワードで行われる
        super(user.getLoginUserId(), user.getPassword(), AuthorityUtils.createAuthorityList("ADMIN", "USER"));
        this.mLoginUser = user;
    }

    public MLoginUser getUser() {
        return mLoginUser;
    }

}