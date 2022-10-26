package edu.chnu.library.controller.ui;

import edu.chnu.library.model.BookCase;
import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.model.Shelf;
import edu.chnu.library.security.AuthService;
import edu.chnu.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
 * @date 04.09.2022 01:16
 * @class ShelfController
 */
@Controller
@RequestMapping("/ui/shelves")
public class ShelfController {
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;
    ShelfService shelfService;
    ExemplarService exemplarService;
    AuthService authService;

    @Autowired
    public ShelfController(LibraryService libraryService, ReadingRoomService readingRoomService,
                           BookCaseService bookCaseService, ShelfService shelfService,
                           ExemplarService exemplarService, AuthService authService) {
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
        this.shelfService = shelfService;
        this.exemplarService = exemplarService;
        this.authService = authService;
    }

    @GetMapping("/show")
    public String articlePage(HttpServletRequest request, Model model) {

        int page = 0;
        int size = 10;
        String number = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("number") != null && !request.getParameter("number").isEmpty()) {
            number = request.getParameter("number");
        }

        model.addAttribute("shelves", shelfService.getByNumberContainingPaginated(number,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "number"))));
        return "shelf/all";
    }

    @GetMapping("/show/{id}")
    public String showArticle(@PathVariable String id, Model model) {
        Shelf shelf = shelfService.get(id);
        model.addAttribute("shelf", shelf);
        model.addAttribute("exemplars", exemplarService.getAllByShelf(shelf));
        return "shelf/show";
    }

    @GetMapping("/create")
    public String showCreateArticle(Model model) {
        ReadingRoom userReadingRoom = authService.getCurrentEmployee().getReadingRoom();
        Map<ReadingRoom, List<BookCase>> bookCasesByRoom = bookCaseService.getByReadingRoom(userReadingRoom).stream()
                .collect(Collectors.groupingBy(BookCase::getReadingRoom));
        ;
        model.addAttribute("shelf", new Shelf());
        model.addAttribute("bookCases", bookCasesByRoom);
        return "shelf/create";
    }

    @PostMapping("/create")
    public String performCreateArticle(Shelf shelf) {
        Shelf newOne = shelfService.create(shelf);
        return "redirect:/ui/shelves/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEditArticle(@PathVariable String id, Model model) {
        ReadingRoom userReadingRoom = authService.getCurrentEmployee().getReadingRoom();
        Shelf shelf = shelfService.get(id);
        if (!shelf.getBookCase().getReadingRoom().equals(userReadingRoom)) {
            return "redirect:/access/denied";
        }
        Map<ReadingRoom, List<BookCase>> bookCasesByRoom = bookCaseService.getByReadingRoom(userReadingRoom).stream()
                .collect(Collectors.groupingBy(BookCase::getReadingRoom));
        ;
        model.addAttribute("shelf", shelf);
        model.addAttribute("bookCases", bookCasesByRoom);
        return "shelf/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEditArticle(@PathVariable String id, Shelf shelf) {
        Shelf updated = shelfService.update(shelf);
        return "redirect:/ui/shelves/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDeleteArticle(@PathVariable String id) {
        ReadingRoom userReadingRoom = authService.getCurrentEmployee().getReadingRoom();
        Shelf shelf = shelfService.get(id);
        if (!shelf.getBookCase().getReadingRoom().equals(userReadingRoom)) {
            return "redirect:/access/denied";
        }
        shelfService.delete(id);
        return "redirect:/ui/shelves/show";
    }
}
