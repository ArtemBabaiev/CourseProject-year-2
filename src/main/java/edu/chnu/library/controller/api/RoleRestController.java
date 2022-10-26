package edu.chnu.library.controller.api;

import edu.chnu.library.model.Role;
import edu.chnu.library.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project microJava_01
 * @date 05.09.2022 23:37
 * @class RoleRestController
 */
@RestController
@RequestMapping("/api/roles")
public class RoleRestController {
    RoleService service;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.service = roleService;
    }

    @ApiOperation(value = "Get all roles", notes = "Returns all roles from DB", httpMethod = "GET", response = List.class, code = 200)
    @GetMapping("/")
    List<Role> showAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Get role by specified id", notes = "Returns roles by ID from DB", httpMethod = "GET", response = Role.class, code = 200)
    @GetMapping("/{id}")
    Role showOne(@PathVariable String id) {
        return service.get(id);
    }

    @ApiOperation(value = "Delete role by specified id", notes = "Deletes roles by ID from DB", httpMethod = "DELETE", response = void.class, code = 200)
    @DeleteMapping("/{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }

    @ApiOperation(value = "Create new role", notes = "Creates roles in DB and returns new Role object", httpMethod = "POST", response = Role.class, code = 200)
    @PostMapping("")
    Role createOne(@RequestBody Role role) {
        return service.create(role);
    }

    @ApiOperation(value = "Update existing role", notes = "Updates roles in DB and returns updated Role object", httpMethod = "PUT", response = Role.class, code = 200)
    @PutMapping("")
    Role UpdateOne(@RequestBody Role role) {
        return service.update(role);
    }
}
