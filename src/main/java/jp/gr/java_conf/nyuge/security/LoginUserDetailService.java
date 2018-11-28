package jp.gr.java_conf.nyuge.security;

import jp.gr.java_conf.nyuge.model.LoginUser;
import jp.gr.java_conf.nyuge.model.LoginUserDetails;
import jp.gr.java_conf.nyuge.model.MLoginUser;
import jp.gr.java_conf.nyuge.repository.LoginUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Primary
@Service
public interface LoginUserDetailService extends UserDetailsService {
    // NOP
}
