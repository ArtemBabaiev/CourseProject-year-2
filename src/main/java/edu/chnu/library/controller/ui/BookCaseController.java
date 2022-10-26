package edu.chnu.library.controller.ui;

import edu.chnu.library.model.BookCase;
import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.security.AuthService;
import edu.chnu.library.service.BookCaseService;
import edu.chnu.library.service.LibraryService;
import edu.chnu.library.service.ReadingRoomService;
import edu.chnu.library.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 03.09.2022 23:55
 * @class BookCaseController
 */
@Controller
@RequestMapping("/ui/bookCases")
public class BookCaseController {
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;
    ShelfService shelfService;
    AuthService authService;

    @Autowired
    public BookCaseController(LibraryService libraryService, ReadingRoomService readingRoomService,
                              BookCaseService bookCaseService, ShelfService shelfService,
                              AuthService authService) {
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
        this.shelfService = shelfService;
        this.authService = authService;

    }

    @GetMapping("/show")
    public String showPage(HttpServletRequest request, Model model) {
        int page = 0;
        int size = 10;
        String number = "";
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("number") != null && !request.getParameter("number").isEmpty()) {
            number = request.getParameter("number");
        }
        model.addAttribute("bookCases", bookCaseService.getByNumberContainingPaginated(number,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "number"))));
        return "bookCase/all";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        BookCase bookCase = bookCaseService.get(id);
        model.addAttribute("bookCase", bookCase);
        model.addAttribute("shelves", shelfService.getByBookCase(bookCase));
        return "bookCase/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        ReadingRoom readingRoom = authService.getCurrentEmployee().getReadingRoom();
        BookCase bookCase = new BookCase();
        bookCase.setReadingRoom(readingRoom);
        model.addAttribute("bookCase", bookCase);
        model.addAttribute("readingRooms", readingRoomService.getByLibrary(readingRoom.getLibrary()));
        return "bookCase/create";
    }

    @PostMapping("/create")
    public String performCreate(BookCase bookCase) {
        BookCase newOne = bookCaseService.create(bookCase);
        return "redirect:/ui/bookCases/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        ReadingRoom userReadingRoom = authService.getCurrentEmployee().getReadingRoom();
        BookCase bookCase = bookCaseService.get(id);
        if (!bookCase.getReadingRoom().getLibrary().equals(userReadingRoom.getLibrary())) {
            return "redirect:/access/denied";
        }
        model.addAttribute("bookCase", bookCase);
        model.addAttribute("readingRooms", readingRoomService.getByLibrary(userReadingRoom.getLibrary()));
        return "bookCase/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEdit(@PathVariable String id, BookCase bookCase) {
        BookCase updated = bookCaseService.update(bookCase);
        return "redirect:/ui/bookCases/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        ReadingRoom userReadingRoom = authService.getCurrentEmployee().getReadingRoom();
        BookCase bookCase = bookCaseService.get(id);
        if (!bookCase.getReadingRoom().getLibrary().equals(userReadingRoom.getLibrary())) {
            return "redirect:/access/denied";
        }
        bookCaseService.delete(id);
        return "redirect:/ui/bookCases/show";
    }
}
