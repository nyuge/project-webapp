package jp.gr.java_conf.nyuge.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

@Configuration
public class IdPasswordAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Identify user
        final String loginId = (String) authentication.getPrincipal();
        IdPasswordAuthenticationCredentials credentials = (IdPasswordAuthenticationCredentials) authentication.getCredentials();

        // Create authentication token
        return new UsernamePasswordAuthenticationToken(loginId, credentials, Arrays.asList(new SimpleGrantedAuthority("USER")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}