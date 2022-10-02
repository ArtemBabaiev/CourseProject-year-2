package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.Article;
import edu.babaiev.libr.service.ArticleService;
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
 * @date 02.09.2022 02:36
 * @class ArticleController
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {
    ArticleService articleService;
    AuthorService authorService;

    @Autowired
    public ArticleController(ArticleService articleService, AuthorService authorService) {
        this.articleService = articleService;
        this.authorService = authorService;
    }

    @GetMapping("/show")
    public String articlePage(HttpServletRequest request, Model model) {

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

        model.addAttribute("articles", articleService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "article/article-all";
    }

    @GetMapping("/show/{id}")
    public String showArticle(@PathVariable String id, Model model) {
        model.addAttribute("article", articleService.get(id));
        return "article/article-show";
    }

    @GetMapping("/create")
    public String showCreateArticle(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("authors", authorService.getAll());
        return "article/article-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateArticle(Article article) {
        Article newOne = articleService.create(article);
        return new RedirectView("/articles/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditArticle(@PathVariable String id, Model model) {
        model.addAttribute("article", articleService.get(id));
        model.addAttribute("authors", authorService.getAll());
        return "article/article-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditArticle(@PathVariable String id, Article article) {
        Article updated = articleService.update(article);
        return new RedirectView("/articles/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteArticle(@PathVariable String id) {
        articleService.delete(id);
        return new RedirectView("/articles/show");
    }
}
