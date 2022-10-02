package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.CollectionType;
import edu.babaiev.libr.service.CollectionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @collectionType artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:00
 * @class CollectionTypeController
 */
@Controller
@RequestMapping("/collectionTypes")
public class CollectionTypeController {
    CollectionTypeService collectionTypeService;

    @Autowired
    public CollectionTypeController(CollectionTypeService collectionTypeService) {
        this.collectionTypeService = collectionTypeService;
    }

    @GetMapping("/show")
    public String collectionTypePage(HttpServletRequest request, Model model) {

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

        model.addAttribute("collectionTypes", collectionTypeService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "collectionType/collectionType-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("collectionType", collectionTypeService.get(id));
        return "collectionType/collectionType-show";
    }

    @GetMapping("/create")
    public String showCreateCollectionType(Model model) {
        model.addAttribute("collectionType", new CollectionType());
        return "collectionType/collectionType-create";
    }

    @PostMapping("/create")
    public RedirectView performCreateCollectionType(CollectionType collectionType) {
        CollectionType newOne = collectionTypeService.create(collectionType);
        return new RedirectView("/collectionTypes/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditCollectionType(@PathVariable String id, Model model) {
        model.addAttribute("collectionType", collectionTypeService.get(id));
        return "collectionType/collectionType-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditCollectionType(@PathVariable String id, CollectionType collectionType) {
        CollectionType updated = collectionTypeService.update(collectionType);
        return new RedirectView("/collectionTypes/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeleteCollectionType(@PathVariable String id) {
        collectionTypeService.delete(id);
        return new RedirectView("/collectionTypes/show");
    }
}
