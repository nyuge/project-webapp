package jp.gr.java_conf.nyuge.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IdPasswordAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    protected IdPasswordAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // Obtain info form request
        final String loginId = (String) request.getAttribute("inputLoginId");
        final String password = (String) request.getAttribute("inputPassword");
        if (StringUtils.isEmpty(loginId) || StringUtils.isEmpty(password)) {
            throw new AuthenticationServiceException("認証に必要な情報が与えられていません");
        }

        // FIXME DBよりID・パスワードを取得しに行く
        if (!loginId.equals("admin") || !password.equals("demo")) {
            throw new AuthenticationServiceException("IDまたはパスワードが違います");
        }

        IdPasswordAuthenticationCredentials credentials = new IdPasswordAuthenticationCredentials(password);
        final IdPasswordAuthenticationToken authToken = new IdPasswordAuthenticationToken(loginId, credentials);

        // Move to identify user phase
        return this.getAuthenticationManager().authenticate(authToken);
    }

//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {}

}
