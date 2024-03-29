package edu.chnu.library.controller.api;

import edu.chnu.library.model.Scientist;
import edu.chnu.library.service.ScientistService;
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
 * @project microJava_01
 * @date 05.09.2022 23:38
 * @class ScientistRestController
 */
@RestController
@RequestMapping("/api/readers/scientist")
public class ScientistRestController {
    ScientistService service;

    @Autowired
    public ScientistRestController(ScientistService scientistService) {
        this.service = scientistService;
    }

    @ApiOperation(value = "Get all scientists", notes = "Returns all scientists from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Scientist> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get scientist by specified id", notes = "Returns scientists by ID from DB", httpMethod = "GET", response = Scientist.class, code = 200)
    @GetMapping("/{id}")
    Scientist showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete scientist by specified id", notes = "Deletes scientists by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new scientist", notes = "Creates scientists in DB and returns new Scientist object", httpMethod = "POST", response = Scientist.class, code = 200)
    @PostMapping("")
    Scientist createOne(@RequestBody Scientist scientist) {
        return service.create(scientist);
    }

    @ApiOperation(value = "Update existing scientist", notes = "Updates scientists in DB and returns updated Scientist object", httpMethod = "PUT", response = Scientist.class, code = 200)
    @PutMapping("")
    Scientist UpdateOne(@RequestBody Scientist scientist) {
        return service.update(scientist);
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "GET", response = Scientist.class, code = 200)
    @GetMapping("/search")
    List<Scientist> search(HttpServletRequest request) {
        String lastName = "";
        Sort sortBy = Sort.by(Sort.Direction.ASC, "id");
        String between1 = "";
        String between2 = "";
        if (request.getParameter("last_name") != null && !request.getParameter("last_name").isEmpty()) {
            lastName = request.getParameter("last_name");
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
        List<Scientist> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByLastNameContainingAndBetween(lastName, between1, between2, sortBy) : service.getAllByLastNameContaining(lastName, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<Scientist> paging(HttpServletRequest request) {
        int page = 0;
        int size = 10;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        return service.getAllPaginated(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "lastName"))).getContent();
    }
}
