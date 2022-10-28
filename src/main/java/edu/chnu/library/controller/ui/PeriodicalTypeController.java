package edu.chnu.library.controller.ui;

import edu.chnu.library.model.PeriodicalType;
import edu.chnu.library.service.PeriodicalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @periodicalType artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:03
 * @class PeriodicalTypeController
 */
@Controller
@RequestMapping("/ui/periodicalTypes")
public class PeriodicalTypeController {
    PeriodicalTypeService periodicalTypeService;

    @Autowired
    public PeriodicalTypeController(PeriodicalTypeService periodicalTypeService) {
        this.periodicalTypeService = periodicalTypeService;
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

        model.addAttribute("periodicalTypes", periodicalTypeService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "periodicalType/all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("periodicalType", periodicalTypeService.get(id));
        return "periodicalType/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("periodicalType", new PeriodicalType());
        return "periodicalType/create";
    }

    @PostMapping("/create")
    public String performCreate(PeriodicalType periodicalType) {
        PeriodicalType newOne = periodicalTypeService.create(periodicalType);
        return "redirect:/ui/periodicalTypes/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("periodicalType", periodicalTypeService.get(id));
        return "periodicalType/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEdit(@PathVariable String id, PeriodicalType periodicalType) {
        PeriodicalType updated = periodicalTypeService.update(periodicalType);
        return "redirect:/ui/periodicalTypes/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        periodicalTypeService.delete(id);
        return "redirect:/ui/periodicalTypes/show";
    }
}
