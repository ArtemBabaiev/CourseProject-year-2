package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.Library;
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
 * @date 04.09.2022 01:12
 * @class LibraryController
 */
@Controller
@RequestMapping("libraries")
public class LibraryController {
    LibraryService libraryService;
    ReadingRoomService readingRoomService;

    @Autowired
    public LibraryController(LibraryService libraryService, ReadingRoomService readingRoomService) {
        this.libraryService = libraryService;
        this.readingRoomService = readingRoomService;
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

        model.addAttribute("libraries", libraryService.getByNumberContainingPaginated(number,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "number"))));
        return "library/library-all";
    }

    @GetMapping("/show/{id}")
    public String showArticle(@PathVariable String id, Model model) {
        Library library = libraryService.get(id);
        model.addAttribute("library", library);
        model.addAttribute("readingRooms", readingRoomService.getByLibrary(library));
        return "library/library-show";
    }

    @GetMapping("/create")
    public String showCreateArticle(Model model) {
        model.addAttribute("library", new Library());
        return "library/library-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateArticle(Library library) {
        Library newOne = libraryService.create(library);
        return new RedirectView("/libraries/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditArticle(@PathVariable String id, Model model) {
        model.addAttribute("library", libraryService.get(id));
        model.addAttribute("libraries", libraryService.getAll());
        return "library/library-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditArticle(@PathVariable String id, Library library) {
        Library updated = libraryService.update(library);
        return new RedirectView("/libraries/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteArticle(@PathVariable String id) {
        libraryService.delete(id);
        return new RedirectView("/libraries/show");
    }
}
