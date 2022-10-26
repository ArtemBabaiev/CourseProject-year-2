package edu.chnu.library.controller.api;

import edu.chnu.library.model.BookCase;
import edu.chnu.library.service.BookCaseService;
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
 * @date 05.09.2022 22:26
 * @class BookCaseRestController
 */
@RestController
@RequestMapping("/api/bookCases")
public class BookCaseRestController {
    BookCaseService service;

    @Autowired
    public BookCaseRestController(BookCaseService bookCaseService) {
        this.service = bookCaseService;
    }

    @ApiOperation(value = "Get all bookCases", notes = "Returns all bookCases from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<BookCase> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get bookCase by specified id", notes = "Returns bookCases by ID from DB", httpMethod = "GET", response = BookCase.class, code = 200)
    @GetMapping("/{id}")
    BookCase showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete bookCase by specified id", notes = "Deletes bookCases by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new bookCase", notes = "Creates bookCases in DB and returns new BookCase object", httpMethod = "POST", response = BookCase.class, code = 200)
    @PostMapping("")
    BookCase createOne(@RequestBody BookCase bookCase) {
        return service.create(bookCase);
    }

    @ApiOperation(value = "Update existing bookCase", notes = "Updates bookCases in DB and returns updated BookCase object", httpMethod = "PUT", response = BookCase.class, code = 200)
    @PutMapping("")
    BookCase UpdateOne(@RequestBody BookCase bookCase) {
        return service.update(bookCase);
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "PUT", response = BookCase.class, code = 200)
    @GetMapping("/search")
    List<BookCase> search(HttpServletRequest request) {
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
        List<BookCase> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByNumberContainingAndBetween(number, between1, between2, sortBy) : service.getAllByNumberContaining(number, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<BookCase> paging(HttpServletRequest request) {
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
