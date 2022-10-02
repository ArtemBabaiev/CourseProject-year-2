package edu.babaiev.libr.controller;

import edu.babaiev.libr.model.Publisher;
import edu.babaiev.libr.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @publisher artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 04.09.2022 01:06
 * @class PublisherController
 */
@Controller
@RequestMapping("/publishers")
public class PublisherController {
    PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/show")
    public String publisherPage(HttpServletRequest request, Model model) {

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

        model.addAttribute("publishers", publisherService.getByNameContainingPaginated(name,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "publisher/publisher-all";
    }

    @GetMapping("/show/{id}")
    public String showOne(@PathVariable String id, Model model) {
        model.addAttribute("publisher", publisherService.get(id));
        return "publisher/publisher-show";
    }

    @GetMapping("/create")
    public String showCreatePublisher(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher/publisher-create";
    }

    @PostMapping("/create")
    public RedirectView performCreatePublisher(Publisher publisher) {
        Publisher newOne = publisherService.create(publisher);
        return new RedirectView("/publishers/show/" + newOne.getId());
    }

    @GetMapping("/edit/{id}")
    public String showEditPublisher(@PathVariable String id, Model model) {
        model.addAttribute("publisher", publisherService.get(id));
        return "publisher/publisher-edit";
    }

    @PutMapping("/edit/{id}")
    public RedirectView performEditPublisher(@PathVariable String id, Publisher publisher) {
        Publisher updated = publisherService.update(publisher);
        return new RedirectView("/publishers/show/" + updated.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView performDeletePublisher(@PathVariable String id) {
        publisherService.delete(id);
        return new RedirectView("/publishers/show");
    }
}
