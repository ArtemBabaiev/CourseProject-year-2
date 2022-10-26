package edu.chnu.library.controller.ui;

import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.security.AuthService;
import edu.chnu.library.service.BookCaseService;
import edu.chnu.library.service.LibraryService;
import edu.chnu.library.service.ReadingRoomService;
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
 * @date 04.09.2022 01:10
 * @class ReadingRoomController
 */
@Controller
@RequestMapping("/ui/readingRooms")
public class ReadingRoomController {
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;

    AuthService authService;

    @Autowired
    public ReadingRoomController(LibraryService libraryService, ReadingRoomService readingRoomService,
                                 BookCaseService bookCaseService, AuthService authService) {
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
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

        model.addAttribute("readingRooms", readingRoomService.getByNumberContainingPaginated(number,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "number"))));
        return "readingRoom/all";
    }

    @GetMapping("/show/{id}")
    public String showArticle(@PathVariable String id, Model model) {
        ReadingRoom readingRoom = readingRoomService.get(id);
        model.addAttribute("readingRoom", readingRoom);
        model.addAttribute("bookCases", bookCaseService.getByReadingRoom(readingRoom));
        return "readingRoom/show";
    }

    @GetMapping("/create")
    public String showCreateArticle(Model model) {
        model.addAttribute("readingRoom", new ReadingRoom());
        model.addAttribute("libraries", libraryService.getAll());
        return "readingRoom/create";
    }

    @PostMapping("/create")
    public String performCreateArticle(ReadingRoom readingRoom) {
        ReadingRoom newOne = readingRoomService.create(readingRoom);
        return "redirect:/ui/readingRooms/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEditArticle(@PathVariable String id, Model model) {
        ReadingRoom userReadingRoom = authService.getCurrentEmployee().getReadingRoom();
        ReadingRoom readingRoom = readingRoomService.get(id);
        if (readingRoom.getLibrary().equals(userReadingRoom.getLibrary())) {
            return "redirect:/access/denied";
        }
        model.addAttribute("readingRoom", readingRoom);
        model.addAttribute("libraries", libraryService.getAll());
        return "readingRoom/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEditArticle(@PathVariable String id, ReadingRoom readingRoom) {
        ReadingRoom updated = readingRoomService.update(readingRoom);
        return "redirect:/ui/readingRooms/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDeleteArticle(@PathVariable String id) {
        ReadingRoom userReadingRoom = authService.getCurrentEmployee().getReadingRoom();
        ReadingRoom readingRoom = readingRoomService.get(id);
        if (readingRoom.getLibrary().equals(userReadingRoom.getLibrary())) {
            return "redirect:/access/denied";
        }
        readingRoomService.delete(id);
        return "redirect:/ui/readingRooms/show";
    }
}
