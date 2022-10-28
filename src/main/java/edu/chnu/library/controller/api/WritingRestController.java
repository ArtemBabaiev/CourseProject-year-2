package edu.chnu.library.controller.api;

import edu.chnu.library.model.Writing;
import edu.chnu.library.service.WritingService;
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
 * @class WritingRestController
 */
@RestController
@RequestMapping("/api/writings")
public class WritingRestController {
    WritingService service;

    @Autowired
    public WritingRestController(WritingService writingService) {
        this.service = writingService;
    }

    @ApiOperation(value = "Get all writings", notes = "Returns all writings from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Writing> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get writing by specified id", notes = "Returns writings by ID from DB", httpMethod = "GET", response = Writing.class, code = 200)
    @GetMapping("/{id}")
    Writing showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete writing by specified id", notes = "Deletes writings by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new writing", notes = "Creates writings in DB and returns new Writing object", httpMethod = "POST", response = Writing.class, code = 200)
    @PostMapping("")
    Writing createOne(@RequestBody Writing writing) {
        return service.create(writing);
    }

    @ApiOperation(value = "Update existing writing", notes = "Updates writings in DB and returns updated Writing object", httpMethod = "PUT", response = Writing.class, code = 200)
    @PutMapping("")
    Writing UpdateOne(@RequestBody Writing writing) {
        return service.update(writing);
    }

    @GetMapping("/top10")
    List<Writing> topTen() {
        return service.getTop10();
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "GET", response = Writing.class, code = 200)
    @GetMapping("/search")
    List<Writing> search(HttpServletRequest request) {
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
        List<Writing> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByNameContainingAndBetween(name, between1, between2, sortBy) : service.getAllByNameContaining(name, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<Writing> paging(HttpServletRequest request) {
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
