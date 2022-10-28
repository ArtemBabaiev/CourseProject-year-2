package edu.chnu.library.controller.ui;

import edu.chnu.library.model.Genre;
import edu.chnu.library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @genre artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:01
 * @class GenreController
 */
@Controller
@RequestMapping("/ui/genres")
public class GenreController {
    GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
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

        model.addAttribute("genres", genreService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "genre/all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("genre", genreService.get(id));
        return "genre/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre/create";
    }

    @PostMapping("/create")
    public String performCreate(Genre genre) {
        Genre newOne = genreService.create(genre);
        return "redirect:/ui/genres/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("genre", genreService.get(id));
        return "genre/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEdit(@PathVariable String id, Genre genre) {
        Genre updated = genreService.update(genre);
        return "redirect:/ui/genres/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        genreService.delete(id);
        return "redirect:/ui/genres/show";
    }
}
