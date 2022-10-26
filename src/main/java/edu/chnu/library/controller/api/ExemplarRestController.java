package edu.chnu.library.controller.api;

import edu.chnu.library.model.Exemplar;
import edu.chnu.library.service.ExemplarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 05.09.2022 22:28
 * @class ExemplarRestController
 */
@RestController
@RequestMapping("/api/exemplars")
public class ExemplarRestController {
    ExemplarService service;

    @Autowired
    public ExemplarRestController(ExemplarService exemplarService) {
        this.service = exemplarService;
    }

    @ApiOperation(value = "Get all exemplars", notes = "Returns all exemplars from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Exemplar> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get exemplar by specified id", notes = "Returns exemplars by ID from DB", httpMethod = "GET", response = Exemplar.class, code = 200)
    @GetMapping("/{id}")
    Exemplar showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete exemplar by specified id", notes = "Deletes exemplars by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new exemplar", notes = "Creates exemplars in DB and returns new Exemplar object", httpMethod = "POST", response = Exemplar.class, code = 200)
    @PostMapping("")
    Exemplar createOne(@RequestBody Exemplar exemplar) {
        return service.create(exemplar);
    }

    @ApiOperation(value = "Update existing exemplar", notes = "Updates exemplars in DB and returns updated Exemplar object", httpMethod = "PUT", response = Exemplar.class, code = 200)
    @PutMapping("")
    Exemplar UpdateOne(@RequestBody Exemplar exemplar) {
        return service.update(exemplar);
    }
}
