package edu.chnu.library.controller.ui;

import edu.chnu.library.form.QueryForm;
import edu.chnu.library.service.CustomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 21.10.2022 11:58
 * @class CustomQueryController
 */
@Controller
@RequestMapping("/ui/query")
public class CustomQueryController {

    CustomQueryService queryService;

    @Autowired
    public CustomQueryController(CustomQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("")
    public String showQuery(Model model) {
        model.addAttribute("form", new QueryForm());
        model.addAttribute("result", null);
        return "query";
    }

    @PostMapping("")
    public String performQuery(QueryForm form, Model model) {
        String result = null;

        try {
            if ("SQL".equals(form.getType())) {
                result = queryService.sqlQueryHandler(form.getQuery());
                String description = queryService.sqlGetNames(form.getQuery());
                model.addAttribute("result", result);
                model.addAttribute("description", description);
            } else if ("MONGO".equals(form.getType())) {
                result = queryService.mongoQueryHandler(form.getQuery());
                model.addAttribute("result", result);
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("result", result);
        }
        model.addAttribute("form", form);
        return "query";
    }
}
