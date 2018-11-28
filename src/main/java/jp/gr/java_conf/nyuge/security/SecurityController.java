package jp.gr.java_conf.nyuge.security;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@EnableAutoConfiguration
public class SecurityController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/403")
    public ModelAndView forbidden() {
        return  new ModelAndView("403");
    }

}