package edu.chnu.library.controller.ui;

import edu.chnu.library.model.Subject;
import edu.chnu.library.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @subject artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:08
 * @class SubjectController
 */
@Controller
@RequestMapping("/ui/subjects")
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
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }

        model.addAttribute("subjects", subjectService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "subject/all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("subject", subjectService.get(id));
        return "subject/show";
    }

    @GetMapping("/create")
    public String showCreateSubject(Model model) {
        model.addAttribute("subject", new Subject());
        return "subject/create";
    }

    @PostMapping("/create")
    public String performCreateSubject(Subject subject) {
        Subject newOne = subjectService.create(subject);
        return "redirect:/ui/subjects/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEditSubject(@PathVariable String id, Model model) {
        model.addAttribute("subject", subjectService.get(id));
        return "subject/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEditSubject(@PathVariable String id, Subject subject) {
        Subject updated = subjectService.update(subject);
        return "redirect:/ui/subjects/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDeleteSubject(@PathVariable String id) {
        subjectService.delete(id);
        return "redirect:/ui/subjects/show";
    }
}
