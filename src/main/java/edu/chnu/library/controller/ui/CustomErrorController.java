package edu.chnu.library.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 26.09.2022 03:14
 * @class CustomErrorController
 */
@Controller
@RequestMapping("/access")
public class CustomErrorController {
    @RequestMapping("/denied")
    String accessDenied() {
        return "error/403";
    }
}
