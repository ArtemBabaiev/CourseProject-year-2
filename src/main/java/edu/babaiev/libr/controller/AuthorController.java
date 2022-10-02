package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.Author;
import edu.babaiev.libr.service.AuthorService;
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
 * @date 31.08.2022 00:27
 * @class AuthorController
 */
@Controller
@RequestMapping("/authors")
public class AuthorController {
    AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/show")
    public String authorPage(HttpServletRequest request, Model model) {

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

        model.addAttribute("authors", authorService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "author/author-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("author", authorService.get(id));
        return "author/author-show";
    }

    @GetMapping("/create")
    public String showCreateAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "author/author-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateAuthor(Author author) {
        Author newOne = authorService.create(author);
        return new RedirectView("/authors/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditAuthor(@PathVariable String id, Model model) {
        model.addAttribute("author", authorService.get(id));
        return "author/author-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditAuthor(@PathVariable String id, Author author) {
        Author updated = authorService.update(author);
        return new RedirectView("/authors/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteAuthor(@PathVariable String id) {
        authorService.delete(id);
        return new RedirectView("/authors/show");
    }


}
