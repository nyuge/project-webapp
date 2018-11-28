package jp.gr.java_conf.nyuge.security;

import jp.gr.java_conf.nyuge.model.LoginUser;
import jp.gr.java_conf.nyuge.model.LoginUserDetails;
import jp.gr.java_conf.nyuge.model.MLoginUser;
import jp.gr.java_conf.nyuge.repository.LoginUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class LoginUserDetailServiceImpl implements LoginUserDetailService {

    @Autowired
    LoginUserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(loginId)) {
            throw new UsernameNotFoundException("LoginId is empty");
        }

        LoginUser user = repository.findByLoginId(loginId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + loginId);
        }

        if (!user.isLoginEnabled()) {
            throw new UsernameNotFoundException("User not found: " + loginId);
        }

        return new LoginUserDetails(user, getAuthorities(user));
    }

    private Collection<GrantedAuthority> getAuthorities(LoginUser user) {

        // FIXME この権限付与のロジックは拡張性ない
        if (user.getRole().equals("ADMIN")) {
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        } else if (user.getRole().equals("USER")) {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        } else {
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
        }

    }

    @Transactional
    public void register(String loginId, String password) {
        MLoginUser mUser = new MLoginUser();
        mUser.setLoginId(loginId);
        mUser.setPassword(passwordEncoder.encode(password));
        LoginUser user = new LoginUser(mUser);

        repository.saveAndFlush(user);
    }

    public LoginUser findByLoginId(String loginId) {
        return repository.findByLoginId(loginId);
    }

    public boolean isAlreadyRegisterd(LoginUser user) {
        return repository.findByLoginId(user.getLoginId()) != null;
    }

    public String generateDummyPassword() {
        return "***";
    }

}