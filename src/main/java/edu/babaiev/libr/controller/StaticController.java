package edu.babaiev.libr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 22.08.2022 19:01
 * @class StaticController
 */
@Controller
@RequestMapping("/")
public class StaticController {

    @GetMapping("")
    public RedirectView showDefaultHome() {
        return new RedirectView("/home");
    }

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }
    @GetMapping("/resources")
    public String showResources(){
        return "resources";
    }
}
