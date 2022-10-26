package edu.chnu.library.controller.api;

import edu.chnu.library.model.Key;
import edu.chnu.library.service.KeyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 05.09.2022 22:29
 * @class KeyRestController
 */
@RestController
@RequestMapping("/api/keys")
public class KeyRestController {
    KeyService service;

    @Autowired
    public KeyRestController(KeyService keyService) {
        this.service = keyService;
    }

    @ApiOperation(value = "Get all keys", notes = "Returns all keys from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Key> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get key by specified id", notes = "Returns keys by ID from DB", httpMethod = "GET", response = Key.class, code = 200)
    @GetMapping("/{id}")
    Key showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete key by specified id", notes = "Deletes keys by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new key", notes = "Creates keys in DB and returns new Key object", httpMethod = "POST", response = Key.class, code = 200)
    @PostMapping("")
    Key createOne(@RequestBody Key key) {
        return service.create(key);
    }

    @ApiOperation(value = "Update existing key", notes = "Updates keys in DB and returns updated Key object", httpMethod = "PUT", response = Key.class, code = 200)
    @PutMapping("")
    Key UpdateOne(@RequestBody Key key) {
        return service.update(key);
    }
}
