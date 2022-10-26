package edu.chnu.library.controller.ui;

import edu.chnu.library.form.ExemplarForm;
import edu.chnu.library.model.*;
import edu.chnu.library.security.MyUserDetails;
import edu.chnu.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 31.08.2022 21:46
 * @class ExemplarController
 */
@Controller
@RequestMapping("/ui/exemplars")
public class ExemplarController {
    ExemplarService exemplarService;
    LiteratureService literatureService;
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;
    ShelfService shelfService;
    EmployeeService employeeService;

    @Autowired
    public ExemplarController(ExemplarService exemplarService, LiteratureService literatureService,
                              LibraryService libraryService, ReadingRoomService readingRoomService,
                              BookCaseService bookCaseService, ShelfService shelfService,
                              EmployeeService employeeService) {
        this.exemplarService = exemplarService;
        this.literatureService = literatureService;
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
        this.shelfService = shelfService;
        this.employeeService = employeeService;
    }

    @GetMapping("/show/for/{id}")
    public String showExemplarsFor(@PathVariable String id, HttpServletRequest request, Model model) {
        Literature literature = literatureService.get(id);
        int page = 0;
        int size = 10;
        String toSearch = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("toSearch") != null && !request.getParameter("toSearch").isEmpty()) {
            toSearch = request.getParameter("toSearch");
        }
        model.addAttribute("literature", literature);
        Page<Exemplar> exemplars = exemplarService.getAllByLiteratureAndIdContainingPaginatedAndReadingRoom(
                literature,
                toSearch,
                getAuthEmployee().getReadingRoom(), PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))
        );
        model.addAttribute("exemplars", exemplars);
        return "exemplar/show";
    }

    @GetMapping("/create/for/{id}")
    public String showAddExemplar(@PathVariable String id, Model model) {
        model.addAttribute("literature", literatureService.get(id));
        ReadingRoom readingRoom = getAuthEmployee().getReadingRoom();
        Map<BookCase, List<Shelf>> casesContent = shelfService.getByReadingRoom(readingRoom).stream()
                .collect(Collectors.groupingBy(Shelf::getBookCase));
        model.addAttribute("shelves", casesContent);
        model.addAttribute("exemplarForm", new ExemplarForm());
        return "exemplar/add";
    }

    @PostMapping("/create/for/{id}")
    public String performAddExemplar(@PathVariable String id, ExemplarForm exemplarForm) {
        Literature literature = literatureService.get(id);
        for (int i = 0; i < exemplarForm.getQuantity(); i++) {
            Exemplar exemplar = new Exemplar();
            exemplar.setLend(false);
            exemplar.setLiterature(literature);
            exemplar.setShelf(exemplarForm.getShelf());
            exemplarService.create(exemplar);
        }
        return "redirect:/ui/exemplars/show/for/" + literature.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDeleteExemplar(@PathVariable String id) {
        String toId = exemplarService.get(id).getLiterature().getId();
        exemplarService.delete(id);
        return "redirect:/ui/exemplars/show/for/" + toId;
    }

    private Employee getAuthEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Key key = ((MyUserDetails) auth.getPrincipal()).getKey();
        return employeeService.getByKey(key);
    }
}
