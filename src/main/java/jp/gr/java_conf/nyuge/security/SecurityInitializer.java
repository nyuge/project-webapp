package jp.gr.java_conf.nyuge.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityInitializer() {
        super(SecurityConfig.class);
    }

    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
