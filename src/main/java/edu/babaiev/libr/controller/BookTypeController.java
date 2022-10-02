package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.BookType;
import edu.babaiev.libr.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @bookType artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 00:58
 * @class BookTypeController
 */
@Controller
@RequestMapping("/bookTypes")
public class BookTypeController {
    BookTypeService bookTypeService;

    @Autowired
    public BookTypeController(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }

    @GetMapping("/show")
    public String bookTypePage(HttpServletRequest request, Model model) {

        int page = 0;
        int size = 10;
        String name = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }

        model.addAttribute("bookTypes", bookTypeService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "bookType/bookType-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("bookType", bookTypeService.get(id));
        return "bookType/bookType-show";
    }

    @GetMapping("/create")
    public String showCreateBookType(Model model) {
        model.addAttribute("bookType", new BookType());
        return "bookType/bookType-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateBookType(BookType bookType) {
        BookType newOne = bookTypeService.create(bookType);
        return new RedirectView("/bookTypes/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditBookType(@PathVariable String id, Model model) {
        model.addAttribute("bookType", bookTypeService.get(id));
        return "bookType/bookType-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditBookType(@PathVariable String id, BookType bookType) {
        BookType updated = bookTypeService.update(bookType);
        return new RedirectView("/bookTypes/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteBookType(@PathVariable String id) {
        bookTypeService.delete(id);
        return new RedirectView("/bookTypes/show");
    }
}
