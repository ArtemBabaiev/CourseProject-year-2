package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.Shelf;
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
 * @date 04.09.2022 01:16
 * @class ShelfController
 */
@Controller
@RequestMapping("shelves")
public class ShelfController {
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;
    ShelfService shelfService;
    ExemplarService exemplarService;

    @Autowired

    public ShelfController(LibraryService libraryService, ReadingRoomService readingRoomService, BookCaseService bookCaseService, ShelfService shelfService, ExemplarService exemplarService) {
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
        this.shelfService = shelfService;
        this.exemplarService = exemplarService;
    }

    @GetMapping("/show")
    public String articlePage(HttpServletRequest request, Model model) {

        int page = 0;
        int size = 10;
        String number = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        if (request.getParameter("number") != null && !request.getParameter("number").isEmpty()) {
            number = request.getParameter("number");
        }

        model.addAttribute("shelves", shelfService.getByNumberContainingPaginated(number,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "number"))));
        return "shelf/shelf-all";
    }

    @GetMapping("/show/{id}")
    public String showArticle(@PathVariable String id, Model model) {
        Shelf shelf = shelfService.get(id);
        model.addAttribute("shelf", shelf);
        model.addAttribute("exemplars", exemplarService.getAllByShelf(shelf));
        return "shelf/shelf-show";
    }

    @GetMapping("/create")
    public String showCreateArticle(Model model) {
        model.addAttribute("shelf", new Shelf());
        model.addAttribute("bookCases", bookCaseService.getAll());
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("libraries", libraryService.getAll());
        return "shelf/shelf-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateArticle(Shelf shelf) {
        Shelf newOne = shelfService.create(shelf);
        return new RedirectView("/shelves/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditArticle(@PathVariable String id, Model model) {
        model.addAttribute("shelf", shelfService.get(id));
        model.addAttribute("bookCases", bookCaseService.getAll());
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("libraries", libraryService.getAll());
        return "shelf/shelf-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditArticle(@PathVariable String id, Shelf shelf) {
        Shelf updated = shelfService.update(shelf);
        return new RedirectView("/shelves/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteArticle(@PathVariable String id) {
        shelfService.delete(id);
        return new RedirectView("/shelves/show");
    }
}
