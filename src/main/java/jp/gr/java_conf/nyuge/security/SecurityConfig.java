package jp.gr.java_conf.nyuge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String ROLE_ADMIN = "ADMIN";

    private final String ROLE_USER = "USER";

//    @Autowired
//    private IdPasswordAuthenticationProvider idPasswordAuthenticationProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    public SecurityConfig() {
        super();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login").successForwardUrl("/hello").failureForwardUrl("/login?error")
                .and()
                .logout().logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll()
                .and()
                .authorizeRequests()
//                .antMatchers("/h2_console/**").access("hasRole('ADMIN') and hasRole('USER')")
                .antMatchers("/admin/**").hasRole(ROLE_ADMIN)
                .antMatchers("/user/**").hasRole(ROLE_USER)
                .antMatchers("/shared/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
//                .anyRequest().authenticated()
//                .and() // settings for h2-console
//                .csrf().disable()
//                .headers().frameOptions().disable()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

//        AntPathRequestMatcher IdPasswordAuthenticationPathRequestMatcher = new AntPathRequestMatcher("/login", "POST");
//        IdPasswordAuthenticationProcessingFilter filter = new IdPasswordAuthenticationProcessingFilter(IdPasswordAuthenticationPathRequestMatcher);
//        filter.setAuthenticationManager(authenticationManagerBean());
//        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login-error"));
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // auth.authenticationProvider(idPasswordAuthenticationProvider);
        auth.inMemoryAuthentication()
                .withUser("agent").password("{noop}demo").roles(ROLE_ADMIN)
                .and()
                .withUser("user").password("{noop}demo").roles(ROLE_USER)
                .and()
                .withUser("sv").password("{noop}demo").roles(ROLE_USER, ROLE_ADMIN);
    }

}
