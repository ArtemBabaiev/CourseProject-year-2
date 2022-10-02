package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.Subject;
import edu.babaiev.libr.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @subject artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:08
 * @class SubjectController
 */
@Controller
@RequestMapping("subjects")
public class SubjectController {
    SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/show")
    public String subjectPage(HttpServletRequest request, Model model) {

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

        model.addAttribute("subjects", subjectService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "subject/subject-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("subject", subjectService.get(id));
        return "subject/subject-show";
    }

    @GetMapping("/create")
    public String showCreateSubject(Model model) {
        model.addAttribute("subject", new Subject());
        return "subject/subject-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateSubject(Subject subject) {
        Subject newOne = subjectService.create(subject);
        return new RedirectView("/subjects/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditSubject(@PathVariable String id, Model model) {
        model.addAttribute("subject", subjectService.get(id));
        return "subject/subject-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditSubject(@PathVariable String id, Subject subject) {
        Subject updated = subjectService.update(subject);
        return new RedirectView("/subjects/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteSubject(@PathVariable String id) {
        subjectService.delete(id);
        return new RedirectView("/subjects/show");
    }
}
