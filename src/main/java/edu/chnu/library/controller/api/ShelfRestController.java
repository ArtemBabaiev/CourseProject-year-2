package edu.chnu.library.controller.api;

import edu.chnu.library.model.Shelf;
import edu.chnu.library.service.ShelfService;
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
 * @class ShelfRestController
 */
@RestController
@RequestMapping("/api/shelves")
public class ShelfRestController {
    ShelfService service;

    @Autowired
    public ShelfRestController(ShelfService shelfService) {
        this.service = shelfService;
    }

    @ApiOperation(value = "Get all shelfs", notes = "Returns all shelves from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Shelf> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get shelf by specified id", notes = "Returns shelves by ID from DB", httpMethod = "GET", response = Shelf.class, code = 200)
    @GetMapping("/{id}")
    Shelf showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete shelf by specified id", notes = "Deletes shelves by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new shelf", notes = "Creates shelves in DB and returns new Shelf object", httpMethod = "POST", response = Shelf.class, code = 200)
    @PostMapping("")
    Shelf createOne(@RequestBody Shelf shelf) {
        return service.create(shelf);
    }

    @ApiOperation(value = "Update existing shelf", notes = "Updates shelves in DB and returns updated Shelf object", httpMethod = "PUT", response = Shelf.class, code = 200)
    @PutMapping("")
    Shelf UpdateOne(@RequestBody Shelf shelf) {
        return service.update(shelf);
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "PUT", response = Shelf.class, code = 200)
    @GetMapping("/search")
    List<Shelf> search(HttpServletRequest request) {
        String number = "";
        Sort sortBy = Sort.by(Sort.Direction.ASC, "id");
        String between1 = "";
        String between2 = "";
        if (request.getParameter("number") != null && !request.getParameter("number").isEmpty()) {
            number = request.getParameter("number");
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
        List<Shelf> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByNumberContainingAndBetween(number, between1, between2, sortBy) : service.getAllByNumberContaining(number, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<Shelf> paging(HttpServletRequest request) {
        int page = 0;
        int size = 10;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        return service.getAllPaginated(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "number"))).getContent();
    }
}
