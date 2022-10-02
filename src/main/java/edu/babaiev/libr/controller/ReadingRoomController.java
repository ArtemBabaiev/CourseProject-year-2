package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.ReadingRoom;
import edu.babaiev.libr.service.BookCaseService;
import edu.babaiev.libr.service.LibraryService;
import edu.babaiev.libr.service.ReadingRoomService;
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
 * @date 04.09.2022 01:10
 * @class ReadingRoomController
 */
@Controller
@RequestMapping("/readingRooms")
public class ReadingRoomController {
    LibraryService libraryService;
    ReadingRoomService readingRoomService;
    BookCaseService bookCaseService;

    @Autowired
    public ReadingRoomController(LibraryService libraryService, ReadingRoomService readingRoomService, BookCaseService bookCaseService) {
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
        this.bookCaseService = bookCaseService;
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

        model.addAttribute("readingRooms", readingRoomService.getByNumberContainingPaginated(number,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "number"))));
        return "readingRoom/readingRoom-all";
    }

    @GetMapping("/show/{id}")
    public String showArticle(@PathVariable String id, Model model) {
        ReadingRoom readingRoom = readingRoomService.get(id);
        model.addAttribute("readingRoom", readingRoom);
        model.addAttribute("bookCases", bookCaseService.getByReadingRoom(readingRoom));
        return "readingRoom/readingRoom-show";
    }

    @GetMapping("/create")
    public String showCreateArticle(Model model) {
        model.addAttribute("readingRoom", new ReadingRoom());
        model.addAttribute("libraries", libraryService.getAll());
        return "readingRoom/readingRoom-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateArticle(ReadingRoom readingRoom) {
        ReadingRoom newOne = readingRoomService.create(readingRoom);
        return new RedirectView("/readingRooms/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditArticle(@PathVariable String id, Model model) {
        model.addAttribute("readingRoom", readingRoomService.get(id));
        model.addAttribute("libraries", libraryService.getAll());
        return "readingRoom/readingRoom-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditArticle(@PathVariable String id, ReadingRoom readingRoom) {
        ReadingRoom updated = readingRoomService.update(readingRoom);
        return new RedirectView("/readingRooms/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteArticle(@PathVariable String id) {
        readingRoomService.delete(id);
        return new RedirectView("/readingRooms/show");
    }
}
