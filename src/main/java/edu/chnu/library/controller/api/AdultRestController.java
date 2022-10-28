package edu.chnu.library.controller.api;

import edu.chnu.library.model.Adult;
import edu.chnu.library.service.AdultService;
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
 * @date 05.09.2022 22:25
 * @class AdultRestController
 */
@RestController
@RequestMapping("/api/readers/adults")
public class AdultRestController {
    AdultService service;

    @Autowired
    public AdultRestController(AdultService adultService) {
        this.service = adultService;
    }

    @ApiOperation(value = "Get all adults", notes = "Returns all adults from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Adult> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get adult by specified id", notes = "Returns adults by ID from DB", httpMethod = "GET", response = Adult.class, code = 200)
    @GetMapping("/{id}")
    Adult showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete adult by specified id", notes = "Deletes adults by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new adult", notes = "Creates adults in DB and returns new Adult object", httpMethod = "POST", response = Adult.class, code = 200)
    @PostMapping("")
    Adult createOne(@RequestBody Adult adult) {

        System.out.println("in method");
        return service.create(adult);
    }

    @ApiOperation(value = "Update existing adult", notes = "Updates adults in DB and returns updated Adult object", httpMethod = "PUT", response = Adult.class, code = 200)
    @PutMapping("")
    Adult UpdateOne(@RequestBody Adult adult) {
        return service.update(adult);
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "GET", response = Adult.class, code = 200)
    @GetMapping("/search")
    List<Adult> search(HttpServletRequest request) {
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
        List<Adult> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByLastNameContainingAndBetween(lastName, between1, between2, sortBy) : service.getAllByLastNameContaining(lastName, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<Adult> paging(HttpServletRequest request) {
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
