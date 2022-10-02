package edu.babaiev.libr.controller;

import edu.babaiev.libr.form.CategoryChooseForm;
import edu.babaiev.libr.form.ReaderCategoryChooseForm;
import edu.babaiev.libr.model.*;
import edu.babaiev.libr.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 11.09.2022 01:01
 * @class ReaderController
 */
@Controller
@RequestMapping("/readers")
public class ReaderController {
    ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/show")
    public String readersPage(HttpServletRequest request, Model model) {

        int page = 0;
        int size = 10;
        String lastName = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        if (request.getParameter("lastName") != null && !request.getParameter("lastName").isEmpty()) {
            lastName = request.getParameter("lastName");
        }

        model.addAttribute("readers", readerService.getByLastNameContainingPaginated(lastName,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "lastName"))));
        return "reader/reader-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        Reader reader = readerService.get(id);
        if (Adult.class.equals(reader.getClass())) {
            model.addAttribute("adult", (Adult) reader);
            return "reader/adult/adult-show";
        }
        if (Pupil.class.equals(reader.getClass())) {
            model.addAttribute("pupil", (Pupil) reader);
            return "reader/pupil/pupil-show";
        }
        if (Scientist.class.equals(reader.getClass())) {
            model.addAttribute("scientist", (Scientist) reader);
            return "reader/scientist/scientist-show";
        }
        if (Student.class.equals(reader.getClass())) {
            model.addAttribute("student", (Student) reader);
            return "reader/student/student-show";
        }
        return "redirect:/readers/show/";
    }

    @GetMapping("/create")
    public String showChooseCategory(Model model) {
        model.addAttribute("readerCategory", new ReaderCategoryChooseForm());
        return "reader/category-choose";
    }

    @PostMapping("/create")
    public RedirectView redirectCategory(ReaderCategoryChooseForm readerCategoryChooseForm) {
        switch (readerCategoryChooseForm.getCategory()) {
            case "Adult":
                return new RedirectView("/readers/create/adult");
            case "Pupil":
                return new RedirectView("/readers/create/pupil");
            case "Scientist":
                return new RedirectView("/readers/create/scientist");
            case "Student":
                return new RedirectView("/readers/create/student");
        }
        return new RedirectView("/readers/create");
    }

    @GetMapping("/create/adult")
    public String showCreateAdult(Model model) {
        model.addAttribute("adult", new Adult());
        return "reader/adult/adult-create";
    }

    @PostMapping("/create/adult")
    public RedirectView performCreateAdult(Adult adult) {
        Reader newOne = readerService.create(adult);
        return new RedirectView("/readers/show/" + newOne.getId());
    }

    @GetMapping("/create/pupil")
    public String showCreatePupil(Model model) {
        model.addAttribute("pupil", new Pupil());
        return "reader/pupil/pupil-create";
    }

    @PostMapping("/create/pupil")
    public RedirectView performCreatePupil(Pupil pupil) {
        Reader newOne = readerService.create(pupil);
        return new RedirectView("/readers/show/" + newOne.getId());
    }

    @GetMapping("/create/scientist")
    public String showCreateScientist(Model model) {
        model.addAttribute("scientist", new Scientist());
        return "reader/scientist/scientist-create";
    }

    @PostMapping("/create/scientist")
    public RedirectView performCreateScientist(Scientist scientist) {
        Reader newOne = readerService.create(scientist);
        return new RedirectView("/readers/show/" + newOne.getId());
    }

    @GetMapping("/create/student")
    public String showCreateStudent(Model model) {
        model.addAttribute("student", new Student());
        return "reader/student/student-create";
    }

    @PostMapping("/create/student")
    public RedirectView performCreateStudent(Student student) {
        Reader newOne = readerService.create(student);
        return new RedirectView("/readers/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        Reader reader = readerService.get(id);
        if (Adult.class.equals(reader.getClass())) {
            model.addAttribute("adult", (Adult) reader);
            return "reader/adult/adult-edit";
        }
        if (Pupil.class.equals(reader.getClass())) {
            model.addAttribute("pupil", (Pupil) reader);
            return "reader/pupil/pupil-edit";
        }
        if (Scientist.class.equals(reader.getClass())) {
            model.addAttribute("scientist", (Scientist) reader);
            return "reader/scientist/scientist-edit";
        }
        if (Student.class.equals(reader.getClass())) {
            model.addAttribute("student", (Student) reader);
            return "reader/student/student-edit";
        }
        return "redirect:/readers/show/";
    }

    @PutMapping("/edit/{id}/adult")
    public RedirectView performEditAdult(@PathVariable String id, Adult adult) {
        Reader updated = readerService.update(adult);
        return new RedirectView("/readers/show/" + updated.getId());
    }

    @PutMapping("/edit/{id}/pupil")
    public RedirectView performEditPupil(@PathVariable String id, Pupil pupil) {
        Reader updated = readerService.update(pupil);
        return new RedirectView("/readers/show/" + updated.getId());
    }

    @PutMapping("/edit/{id}/scientist")
    public RedirectView performEditScientist(@PathVariable String id, Scientist scientist) {
        Reader updated = readerService.update(scientist);
        return new RedirectView("/readers/show/" + updated.getId());
    }

    @PutMapping("/edit/{id}/student")
    public RedirectView performEditStudent(@PathVariable String id, Student student) {
        Reader updated = readerService.update(student);
        return new RedirectView("/readers/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDelete(@PathVariable String id) {
        readerService.delete(id);
        return new RedirectView("/readers/show");

    }
}
