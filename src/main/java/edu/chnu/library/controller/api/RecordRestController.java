package edu.chnu.library.controller.api;

import edu.chnu.library.model.Record;
import edu.chnu.library.service.RecordService;
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
 * @date 05.09.2022 23:36
 * @class RecordRestController
 */
@RestController
@RequestMapping("/api/records")
public class RecordRestController {
    RecordService service;

    @Autowired
    public RecordRestController(RecordService recordService) {
        this.service = recordService;
    }

    @ApiOperation(value = "Get all records", notes = "Returns all records from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Record> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get record by specified id", notes = "Returns records by ID from DB", httpMethod = "GET", response = Record.class, code = 200)
    @GetMapping("/{id}")
    Record showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete record by specified id", notes = "Deletes records by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new record", notes = "Creates records in DB and returns new Record object", httpMethod = "POST", response = Record.class, code = 200)
    @PostMapping("")
    Record createOne(@RequestBody Record record) {
        return service.create(record);
    }

    @ApiOperation(value = "Update existing record", notes = "Updates records in DB and returns updated Record object", httpMethod = "PUT", response = Record.class, code = 200)
    @PutMapping("")
    Record UpdateOne(@RequestBody Record record) {
        return service.update(record);
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "PUT", response = Record.class, code = 200)
    @GetMapping("/search")
    List<Record> search(HttpServletRequest request) {
        String lastName = "";
        Sort sortBy = Sort.by(Sort.Direction.ASC, "id");
        String between1 = "";
        String between2 = "";
        if (request.getParameter("lastName") != null && !request.getParameter("lastName").isEmpty()) {
            lastName = request.getParameter("lastName");
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
        List<Record> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByReaderLastNameContainingAndBetween(lastName, between1, between2, sortBy) : service.getAllByReaderLastNameContaining(lastName, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<Record> paging(HttpServletRequest request) {
        int page = 0;
        int size = 10;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        return service.getAllPaginated(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }
}
