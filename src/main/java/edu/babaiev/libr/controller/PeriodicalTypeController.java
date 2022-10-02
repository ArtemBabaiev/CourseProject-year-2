package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.PeriodicalType;
import edu.babaiev.libr.service.PeriodicalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @periodicalType artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:03
 * @class PeriodicalTypeController
 */
@Controller
@RequestMapping("periodicalTypes")
public class PeriodicalTypeController {
    PeriodicalTypeService periodicalTypeService;

    @Autowired
    public PeriodicalTypeController(PeriodicalTypeService periodicalTypeService) {
        this.periodicalTypeService = periodicalTypeService;
    }

    @GetMapping("/show")
    public String periodicalTypePage(HttpServletRequest request, Model model) {

        int page = 0;
        int size = 10;
        String name = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }

        model.addAttribute("periodicalTypes", periodicalTypeService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "periodicalType/periodicalType-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("periodicalType", periodicalTypeService.get(id));
        return "periodicalType/periodicalType-show";
    }

    @GetMapping("/create")
    public String showCreatePeriodicalType(Model model) {
        model.addAttribute("periodicalType", new PeriodicalType());
        return "periodicalType/periodicalType-create";
    }

    @PostMapping("/create")
    public RedirectView performCreatePeriodicalType(PeriodicalType periodicalType) {
        PeriodicalType newOne = periodicalTypeService.create(periodicalType);
        return new RedirectView("/periodicalTypes/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditPeriodicalType(@PathVariable String id, Model model) {
        model.addAttribute("periodicalType", periodicalTypeService.get(id));
        return "periodicalType/periodicalType-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditPeriodicalType(@PathVariable String id, PeriodicalType periodicalType) {
        PeriodicalType updated = periodicalTypeService.update(periodicalType);
        return new RedirectView("/periodicalTypes/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeletePeriodicalType(@PathVariable String id) {
        periodicalTypeService.delete(id);
        return new RedirectView("/periodicalTypes/show");
    }
}
