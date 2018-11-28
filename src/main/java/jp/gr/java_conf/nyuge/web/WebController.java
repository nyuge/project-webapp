package jp.gr.java_conf.nyuge.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.unbescape.html.HtmlEscape;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@RestController
@EnableAutoConfiguration
public class WebController {

    @RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/index";
    }

    @RequestMapping("index")
    public ModelAndView index(HttpSession session) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("mySessionAttribute", "someValue");
        return mav;
    }

    /** User zone index. */
    @RequestMapping("/user/index.html")
    public ModelAndView userIndex() {
        return new ModelAndView("user/index");
    }

    @RequestMapping("/admin/index.html")
    public ModelAndView adminIndex() {
        return new ModelAndView("admin/index");
    }

    /** Shared zone index. */
    @RequestMapping("/shared/index.html")
    public ModelAndView sharedIndex() {
        return new ModelAndView("shared/index");
    }

    /** Simulation of an exception. */
    @RequestMapping("/simulateError.html")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    @RequestMapping("/hello")
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("message", "direct");
        return mav;
    }

    /**
     * Error page.
     * TODO URLと処理を疎通させる
     */
    @RequestMapping("/application-error")
    public String error(HttpServletRequest request, Model model) {

        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

}
