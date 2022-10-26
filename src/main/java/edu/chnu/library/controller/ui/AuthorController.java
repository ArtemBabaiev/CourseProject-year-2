package edu.chnu.library.controller.ui;

import edu.chnu.library.model.Author;
import edu.chnu.library.service.AuthorService;
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
 * @date 31.08.2022 00:27
 * @class AuthorController
 */
@Controller
@RequestMapping("/ui/authors")
public class AuthorController {
    AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
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

        model.addAttribute("authors", authorService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "author/all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("author", authorService.get(id));
        return "author/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("author", new Author());
        return "author/create";
    }

    @PostMapping("/create")
    public String performCreate(Author author) {
        Author newOne = authorService.create(author);
        return "redirect:/ui/authors/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("author", authorService.get(id));
        return "author/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEdit(@PathVariable String id, Author author) {
        Author updated = authorService.update(author);
        return "redirect:/ui/authors/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        authorService.delete(id);
        return "redirect:/ui/authors/show";
    }
}
