package edu.chnu.library.controller.ui;

import edu.chnu.library.model.MonographType;
import edu.chnu.library.service.MonographTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @monographType artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:03
 * @class MonographTypeController
 */
@Controller
@RequestMapping("/ui/monographTypes")
public class MonographTypeController {
    MonographTypeService monographTypeService;

    @Autowired
    public MonographTypeController(MonographTypeService monographTypeService) {
        this.monographTypeService = monographTypeService;
    }

    @GetMapping("/show")
    public String showPage(HttpServletRequest request, Model model) {

        int page = 0;
        int size = 10;
        String name = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }
        model.addAttribute("monographTypes", monographTypeService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "monographType/all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("monographType", monographTypeService.get(id));
        return "monographType/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("monographType", new MonographType());
        return "monographType/create";
    }

    @PostMapping("/create")
    public String performCreate(MonographType monographType) {
        MonographType newOne = monographTypeService.create(monographType);
        return "redirect:/ui/monographTypes/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("monographType", monographTypeService.get(id));
        return "monographType/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEdit(@PathVariable String id, MonographType monographType) {
        MonographType updated = monographTypeService.update(monographType);
        return "redirect:/ui/monographTypes/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        monographTypeService.delete(id);
        return "redirect:/ui/monographTypes/show";
    }
}
