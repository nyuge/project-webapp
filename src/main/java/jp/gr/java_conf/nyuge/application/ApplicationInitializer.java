package jp.gr.java_conf.nyuge.application;

import jp.gr.java_conf.nyuge.security.SecurityConfig;
import jp.gr.java_conf.nyuge.web.WebConfig;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


@Order(1)
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String CHARACTER_ENCODING = "UTF-8";

    public ApplicationInitializer() {
        super();
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SecurityConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(CHARACTER_ENCODING);
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }

}