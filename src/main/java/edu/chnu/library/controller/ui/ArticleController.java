package edu.chnu.library.controller.ui;

import edu.chnu.library.model.Article;
import edu.chnu.library.service.ArticleService;
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
 * @date 02.09.2022 02:36
 * @class ArticleController
 */
@Controller
@RequestMapping("/ui/articles")
public class ArticleController {
    ArticleService articleService;
    AuthorService authorService;

    @Autowired
    public ArticleController(ArticleService articleService, AuthorService authorService) {
        this.articleService = articleService;
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

        model.addAttribute("articles",
                articleService.getByNameContainingPaginated(name, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "article/all";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("article", articleService.get(id));
        return "article/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("authors", authorService.getAll());
        return "article/create";
    }

    @PostMapping("/create")
    public String performCreate(Article article) {
        Article newOne = articleService.create(article);
        return "redirect:/ui/articles/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("article", articleService.get(id));
        model.addAttribute("authors", authorService.getAll());
        return "article/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEdit(@PathVariable String id, Article article) {
        Article updated = articleService.update(article);
        return "redirect:/ui/articles/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        articleService.delete(id);
        return "redirect:/ui/articles/show/";
    }
}
