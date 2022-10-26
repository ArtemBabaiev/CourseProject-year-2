package edu.chnu.library.controller.ui;

import edu.chnu.library.form.ForgetPasswordForm;
import edu.chnu.library.security.MyUserDetails;
import edu.chnu.library.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 30.09.2022 11:43
 * @class LoginController
 */
@Controller
public class LoginController {
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    public LoginController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "auth/login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "auth/login";
    }

    @RequestMapping(value = "/forgetPassword")
    public String showForgetPassword(Model model) {
        model.addAttribute("forgetPassword", new ForgetPasswordForm());
        return "auth/forget-password-form";
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public String performForgetPassword(ForgetPasswordForm forgetPassword, Model model) {
        MyUserDetails details = userDetailsService.getDetailsByLogin(forgetPassword.getUsername());
        if (details != null) {
            String password = details.getPassword();
            model.addAttribute("password", password);
            return "auth/forget-password-result";
        }
        model.addAttribute("passwordError", true);
        model.addAttribute("forgetPassword", forgetPassword);
        return "auth/forget-password-form";
    }
}
