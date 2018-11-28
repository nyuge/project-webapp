package jp.gr.java_conf.nyuge.repository;

import jp.gr.java_conf.nyuge.model.LoginUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserRepository extends JpaRepository<LoginUser, Integer> {

    LoginUser findByLoginId(String loginId);

}