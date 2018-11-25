package jp.gr.java_conf.nyuge.security;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@EnableAutoConfiguration
public class SecurityController {

    @RequestMapping("/login.html")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/login-error.html")
    public ModelAndView loginError() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("isError", true);
        mav.addObject("errorMessage", "ユーザーIDまたはパスワードが違います");
        return mav;
    }

    @RequestMapping("/403.html")
    public ModelAndView forbidden() {
        return  new ModelAndView("403");
    }

}