package edu.chnu.library.controller.api;

import edu.chnu.library.model.Monograph;
import edu.chnu.library.service.MonographService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 05.09.2022 22:29
 * @class MonographRestController
 */
@RestController
@RequestMapping("/api/literature/monographs")
public class MonographRestController {
    MonographService service;

    @Autowired
    public MonographRestController(MonographService monographService) {
        this.service = monographService;
    }

    @ApiOperation(value = "Get all monographs", notes = "Returns all monographs from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Monograph> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get monograph by specified id", notes = "Returns monographs by ID from DB", httpMethod = "GET", response = Monograph.class, code = 200)
    @GetMapping("/{id}")
    Monograph showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete monograph by specified id", notes = "Deletes monographs by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new monograph", notes = "Creates monographs in DB and returns new Monograph object", httpMethod = "POST", response = Monograph.class, code = 200)
    @PostMapping("")
    Monograph createOne(@RequestBody Monograph monograph) {
        return service.create(monograph);
    }

    @ApiOperation(value = "Update existing monograph", notes = "Updates monographs in DB and returns updated Monograph object", httpMethod = "PUT", response = Monograph.class, code = 200)
    @PutMapping("")
    Monograph UpdateOne(@RequestBody Monograph monograph) {
        return service.update(monograph);
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "GET", response = Monograph.class, code = 200)
    @GetMapping("/search")
    List<Monograph> search(HttpServletRequest request) {
        String name = "";
        Sort sortBy = Sort.by(Sort.Direction.ASC, "id");
        String between1 = "";
        String between2 = "";
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }
        if (request.getParameter("sort_by") != null && !request.getParameter("sort_by").isEmpty()) {
            StringBuilder requestParameter = new StringBuilder(request.getParameter("sort_by"));
            if (requestParameter.charAt(0) == '+' || requestParameter.charAt(0) == '-') {
                sortBy = Sort.by(requestParameter.charAt(0) == '+' ? Sort.Direction.ASC : Sort.Direction.DESC, requestParameter.substring(1));
            }
        }
        if (request.getParameter("range") != null && !request.getParameter("range").isEmpty()) {
            String[] ranges = request.getParameter("range").split("-");
            try {
                between1 = ranges[0];
                between2 = ranges[1];
            } catch (Exception e) {
            }
        }
        List<Monograph> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByNameContainingAndBetween(name, between1, between2, sortBy) : service.getAllByNameContaining(name, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<Monograph> paging(HttpServletRequest request) {
        int page = 0;
        int size = 10;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        return service.getAllPaginated(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "name"))).getContent();
    }
}
