package jp.gr.java_conf.nyuge;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@EnableAutoConfiguration
public class HelloController {

//    @RequestMapping({"/"})
//    public String index(HttpSession session) {
//        session.setAttribute("mySessionAttribute", "someValue");
//
//        return "index";
//    }

    @GetMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("message", "Hello Thymeleaf!!");

        return mav;
    }
}