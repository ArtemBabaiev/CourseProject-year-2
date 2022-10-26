package edu.chnu.library.controller.api;


import edu.chnu.library.model.Book;
import edu.chnu.library.service.BookService;
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
 * @class BookRestController
 */
@RestController
@RequestMapping("/api/books")
public class BookRestController {
    BookService service;

    @Autowired
    public BookRestController(BookService bookService) {
        this.service = bookService;
    }

    @ApiOperation(value = "Get all books", notes = "Returns all books from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Book> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get book by specified id", notes = "Returns books by ID from DB", httpMethod = "GET", response = Book.class, code = 200)
    @GetMapping("/{id}")
    Book showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete book by specified id", notes = "Deletes books by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new book", notes = "Creates books in DB and returns new Book object", httpMethod = "POST", response = Book.class, code = 200)
    @PostMapping("")
    Book createOne(@RequestBody Book book) {
        return service.create(book);
    }

    @ApiOperation(value = "Update existing book", notes = "Updates books in DB and returns updated Book object", httpMethod = "PUT", response = Book.class, code = 200)
    @PutMapping("")
    Book UpdateOne(@RequestBody Book book) {
        return service.update(book);
    }

    @ApiOperation(value = "Search by name containing and sort by specified field and order", notes = "to specify order put sort_by=+field_name or -fieldName", httpMethod = "PUT", response = Book.class, code = 200)
    @GetMapping("/search")
    List<Book> search(HttpServletRequest request) {
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
        List<Book> result = !Objects.equals(between1, "") && !Objects.equals(between2, "") ? service.getAllByNameContainingAndBetween(name, between1, between2, sortBy) : service.getAllByNameContaining(name, sortBy);
        return result;
    }

    @GetMapping("/paging")
    List<Book> paging(HttpServletRequest request) {
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
