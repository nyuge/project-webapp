package jp.gr.java_conf.nyuge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import jp.gr.java_conf.nyuge.security.SecurityConfig;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import({ SecurityConfig.class })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}