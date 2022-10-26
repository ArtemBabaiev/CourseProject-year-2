package edu.chnu.library.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping(value = {"ui/home", ""})
    public String showHome() {
        return "home";
    }

    @GetMapping("ui/resources")
    public String showResources() {
        return "resources";
    }

    @GetMapping("/ui/control")
    public String showControl(){
        return "control";
    }
}
