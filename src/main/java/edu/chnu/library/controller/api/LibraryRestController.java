package edu.chnu.library.controller.api;

import edu.chnu.library.model.Library;
import edu.chnu.library.model.Library;
import edu.chnu.library.service.LibraryService;
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
 * @class LibraryRestController
 */
@RestController
@RequestMapping("/api/libraries")
public class LibraryRestController {
    LibraryService service;

    @Autowired
    public LibraryRestController(LibraryService libraryService) {
        this.service = libraryService;
    }

    @ApiOperation(value = "Get all libraries", notes = "Returns all libraries from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Library> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get library by specified id", notes = "Returns libraries by ID from DB", httpMethod = "GET", response = Library.class, code = 200)
    @GetMapping("/{id}")
    Library showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete library by specified id", notes = "Deletes libraries by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new library", notes = "Creates libraries in DB and returns new Library object", httpMethod = "POST", response = Library.class, code = 200)
    @PostMapping("")
    Library createOne(@RequestBody Library library) {
        return service.create(library);
    }

    @ApiOperation(value = "Update existing library", notes = "Updates libraries in DB and returns updated Library object", httpMethod = "PUT", response = Library.class, code = 200)
    @PutMapping("")
    Library UpdateOne(@RequestBody Library library) {
        return service.update(library);
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "PUT", response = Library.class, code = 200)
    @GetMapping("/search")
    List<Library> search(HttpServletRequest request) {
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
            }catch (Exception e){}
        }
        List<Library> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByNumberContainingAndBetween(number, between1, between2, sortBy) : service.getAllByNumberContaining(number, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<Library> paging(HttpServletRequest request){
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
