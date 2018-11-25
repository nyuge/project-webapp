package jp.gr.java_conf.nyuge.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_USER = "USER";

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login.html").defaultSuccessUrl("/index.html").failureUrl("/login-error.html")
                .usernameParameter("inputLoginId").passwordParameter("inputPassword")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login.html").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                .antMatchers("/h2_console/**").hasRole(ROLE_ADMIN)
                .antMatchers("/admin/**").hasRole(ROLE_ADMIN)
                .antMatchers("/user/**").hasRole(ROLE_USER)
                .anyRequest().authenticated()
//                .and() // settings for h2-console
//                .csrf().disable()
//                .headers().frameOptions().disable()
                .and()
                .exceptionHandling().accessDeniedPage("/403.html");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("agent").password("{noop}demo").roles(ROLE_ADMIN)
                .and()
                .withUser("user").password("{noop}demo").roles(ROLE_USER)
                .and()
                .withUser("sv").password("{noop}demo").roles(ROLE_USER, ROLE_ADMIN);
    }

//    @Configuration
//    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//        @Autowired
//        UserDetailsServiceImpl userDetailsService;
//
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            // 認証するユーザーを設定する
//            auth.userDetailsService(userDetailsService);
//                    // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
//                    .passwordEncoder(new BCryptPasswordEncoder())
//
//        }
//    }

}
