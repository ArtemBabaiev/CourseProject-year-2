package edu.babaiev.libr.controller;

import edu.babaiev.libr.form.ExemplarForm;
import edu.babaiev.libr.model.Exemplar;
import edu.babaiev.libr.model.Literature;
import edu.babaiev.libr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 31.08.2022 21:46
 * @class ExemplarController
 */
@Controller
@RequestMapping("/exemplars")
public class ExemplarController {
    ExemplarService exemplarService;
    LiteratureService literatureService;
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;
    ShelfService shelfService;

    @Autowired
    public ExemplarController(ExemplarService exemplarService, LiteratureService literatureService, LibraryService libraryService, ReadingRoomService readingRoomService, BookCaseService bookCaseService, ShelfService shelfService) {
        this.exemplarService = exemplarService;
        this.literatureService = literatureService;
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
        this.shelfService = shelfService;
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

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        if (request.getParameter("toSearch") != null && !request.getParameter("toSearch").isEmpty()) {
            toSearch = request.getParameter("toSearch");
        }
        model.addAttribute("literature", literature);
        model.addAttribute("exemplars", exemplarService.getAllByLiteratureContainingIdPaginated(literature, toSearch, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))));
        return "exemplar/exemplar-show";
    }

    @GetMapping("/create/for/{id}")
    public String showAddExemplar(@PathVariable String id, Model model) {
        model.addAttribute("literature", literatureService.get(id));
        model.addAttribute("libraries", libraryService.getAll());
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("bookCases", bookCaseService.getAll());
        model.addAttribute("shelves", shelfService.getAll());
        model.addAttribute("exemplarForm", new ExemplarForm());
        return "exemplar/exemplar-add";
    }

    @PostMapping("/create/for/{id}")
    public RedirectView performAddExemplar(@PathVariable String id, ExemplarForm exemplarForm) {
        Literature literature = literatureService.get(id);
        for (int i = 0; i < exemplarForm.getQuantity(); i++) {
            Exemplar exemplar = new Exemplar();
            exemplar.setLend(false);
            exemplar.setLiterature(literature);
            exemplar.setShelf(exemplarForm.getShelf());
            exemplarService.create(exemplar);
        }
        return new RedirectView("/literature/show/" + literature.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteExemplar(@PathVariable String id) {
        String toId = exemplarService.get(id).getLiterature().getId();
        exemplarService.delete(id);
        return new RedirectView("literature/show/" + toId);
    }

}
