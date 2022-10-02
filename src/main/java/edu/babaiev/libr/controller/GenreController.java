package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.Genre;
import edu.babaiev.libr.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @genre artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:01
 * @class GenreController
 */
@Controller
@RequestMapping("genres")
public class GenreController {
    GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/show")
    public String genrePage(HttpServletRequest request, Model model) {

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

        model.addAttribute("genres", genreService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "genre/genre-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("genre", genreService.get(id));
        return "genre/genre-show";
    }

    @GetMapping("/create")
    public String showCreateGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre/genre-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateGenre(Genre genre) {
        Genre newOne = genreService.create(genre);
        return new RedirectView("/genres/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditGenre(@PathVariable String id, Model model) {
        model.addAttribute("genre", genreService.get(id));
        return "genre/genre-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditGenre(@PathVariable String id, Genre genre) {
        Genre updated = genreService.update(genre);
        return new RedirectView("/genres/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteGenre(@PathVariable String id) {
        genreService.delete(id);
        return new RedirectView("/genres/show");
    }
}
