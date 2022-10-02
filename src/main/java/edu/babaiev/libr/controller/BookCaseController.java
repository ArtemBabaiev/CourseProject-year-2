package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.BookCase;
import edu.babaiev.libr.service.BookCaseService;
import edu.babaiev.libr.service.LibraryService;
import edu.babaiev.libr.service.ReadingRoomService;
import edu.babaiev.libr.service.ShelfService;
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
 * @date 03.09.2022 23:55
 * @class BookCaseController
 */
@Controller
@RequestMapping("/bookCases")
public class BookCaseController {
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;
    ShelfService shelfService;

    @Autowired
    public BookCaseController(LibraryService libraryService, ReadingRoomService readingRoomService, BookCaseService bookCaseService, ShelfService shelfService) {
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
        this.shelfService = shelfService;
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

        model.addAttribute("bookCases", bookCaseService.getByNumberContainingPaginated(number,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "number"))));
        return "bookCase/bookCase-all";
    }

    @GetMapping("/show/{id}")
    public String showArticle(@PathVariable String id, Model model) {
        BookCase bookCase = bookCaseService.get(id);
        model.addAttribute("bookCase", bookCase);
        model.addAttribute("shelves", shelfService.getByBookCase(bookCase));
        return "bookCase/bookCase-show";
    }

    @GetMapping("/create")
    public String showCreateArticle(Model model) {
        model.addAttribute("bookCase", new BookCase());
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("libraries", libraryService.getAll());
        return "bookCase/bookCase-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateArticle(BookCase bookCase) {
        BookCase newOne = bookCaseService.create(bookCase);
        return new RedirectView("/bookCases/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditArticle(@PathVariable String id, Model model) {
        model.addAttribute("bookCase", bookCaseService.get(id));
        model.addAttribute("readingRooms", readingRoomService.getAll());
        model.addAttribute("libraries", libraryService.getAll());
        return "bookCase/bookCase-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditArticle(@PathVariable String id, BookCase bookCase) {
        BookCase updated = bookCaseService.update(bookCase);
        return new RedirectView("/bookCases/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteArticle(@PathVariable String id) {
        bookCaseService.delete(id);
        return new RedirectView("/bookCases/show");
    }
}
