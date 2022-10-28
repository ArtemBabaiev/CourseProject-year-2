package edu.chnu.library.controller.ui;

import edu.chnu.library.model.Library;
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
 * @date 04.09.2022 01:12
 * @class LibraryController
 */
@Controller
@RequestMapping("/ui/libraries")
public class LibraryController {
    LibraryService libraryService;
    ReadingRoomService readingRoomService;

    @Autowired
    public LibraryController(LibraryService libraryService, ReadingRoomService readingRoomService) {
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
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

        model.addAttribute("libraries", libraryService.getByNumberContainingPaginated(number,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "number"))));
        return "library/all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        Library library = libraryService.get(id);
        model.addAttribute("library", library);
        model.addAttribute("readingRooms", readingRoomService.getByLibrary(library));
        return "library/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("library", new Library());
        return "library/create";
    }

    @PostMapping("/create")
    public String performCreateArticle(Library library) {
        Library newOne = libraryService.create(library);
        return "redirect:/ui/libraries/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("library", libraryService.get(id));
        model.addAttribute("libraries", libraryService.getAll());
        return "library/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEdit(@PathVariable String id, Library library) {
        Library updated = libraryService.update(library);
        return "redirect:/ui/libraries/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        libraryService.delete(id);
        return "redirect:/ui/libraries/show";
    }
}
