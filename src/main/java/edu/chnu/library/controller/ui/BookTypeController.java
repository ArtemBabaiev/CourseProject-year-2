package edu.chnu.library.controller.ui;

import edu.chnu.library.model.BookType;
import edu.chnu.library.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @bookType artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 00:58
 * @class BookTypeController
 */
@Controller
@RequestMapping("/ui/bookTypes")
public class BookTypeController {
    BookTypeService bookTypeService;

    @Autowired
    public BookTypeController(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }

    @GetMapping("/show")
    public String showPage(HttpServletRequest request, Model model) {

        int page = 0;
        int size = 10;
        String name = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }

        model.addAttribute("bookTypes", bookTypeService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "bookType/all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("bookType", bookTypeService.get(id));
        return "bookType/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("bookType", new BookType());
        return "bookType/create";
    }

    @PostMapping("/create")
    public String performCreate(BookType bookType) {
        BookType newOne = bookTypeService.create(bookType);
        return "redirect:/ui/bookTypes/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("bookType", bookTypeService.get(id));
        return "bookType/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEdit(@PathVariable String id, BookType bookType) {
        BookType updated = bookTypeService.update(bookType);
        return "redirect:/ui/bookTypes/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        bookTypeService.delete(id);
        return "redirect:/ui/bookTypes/show";
    }
}
