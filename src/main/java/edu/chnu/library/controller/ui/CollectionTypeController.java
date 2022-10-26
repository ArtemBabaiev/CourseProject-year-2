package edu.chnu.library.controller.ui;

import edu.chnu.library.model.CollectionType;
import edu.chnu.library.service.CollectionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @collectionType artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:00
 * @class CollectionTypeController
 */
@Controller
@RequestMapping("/ui/collectionTypes")
public class CollectionTypeController {
    CollectionTypeService collectionTypeService;

    @Autowired
    public CollectionTypeController(CollectionTypeService collectionTypeService) {
        this.collectionTypeService = collectionTypeService;
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

        model.addAttribute("collectionTypes", collectionTypeService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "collectionType/all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("collectionType", collectionTypeService.get(id));
        return "collectionType/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("collectionType", new CollectionType());
        return "collectionType/create";
    }

    @PostMapping("/create")
    public String performCreate(CollectionType collectionType) {
        CollectionType newOne = collectionTypeService.create(collectionType);
        return "redirect:/ui/collectionTypes/show/" + newOne.getId();
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("collectionType", collectionTypeService.get(id));
        return "collectionType/edit";
    }

    @PutMapping("/edit/{id}")
    public String performEdit(@PathVariable String id, CollectionType collectionType) {
        CollectionType updated = collectionTypeService.update(collectionType);
        return "redirect:/ui/collectionTypes/show/" + updated.getId();
    }

    @DeleteMapping("/delete/{id}")
    public String performDelete(@PathVariable String id) {
        collectionTypeService.delete(id);
        return "redirect:/ui/collectionTypes/show";
    }
}
