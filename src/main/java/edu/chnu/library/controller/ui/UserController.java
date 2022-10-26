package edu.chnu.library.controller.ui;

import edu.chnu.library.form.UserForm;
import edu.chnu.library.model.Employee;
import edu.chnu.library.model.Key;
import edu.chnu.library.model.Library;
import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.service.EmployeeService;
import edu.chnu.library.service.KeyService;
import edu.chnu.library.service.ReadingRoomService;
import edu.chnu.library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 26.09.2022 02:33
 * @class UserController
 */
@Controller
@RequestMapping("/ui/users")
public class UserController {
    KeyService keyService;
    EmployeeService employeeService;

    ReadingRoomService readingRoomService;

    RoleService roleService;

    @Autowired
    public UserController(KeyService keyService, EmployeeService employeeService,
                          ReadingRoomService readingRoomService, RoleService roleService) {
        this.keyService = keyService;
        this.employeeService = employeeService;
        this.readingRoomService = readingRoomService;
        this.roleService = roleService;
    }

    @GetMapping("/show")
    public String showPage(HttpServletRequest request, Model model) {

        int page = 0;
        int size = 10;
        String name = "";

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            name = request.getParameter("name");
        }

        model.addAttribute("users",
                employeeService.getByNameContainingPaginated(name, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"))));
        return "user/all";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        Employee employee = employeeService.get(id);
        model.addAttribute("employee", employee);
        return "user/show";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("form", new UserForm());
        Map<Library, List<ReadingRoom>> collect = readingRoomService.getAll().stream().collect(Collectors.groupingBy(ReadingRoom::getLibrary));
        model.addAttribute("rooms", collect);
        model.addAttribute("roles", roleService.getAll());
        return "user/create";
    }

    @PostMapping("/create")
    public String performCreate(UserForm form) {
        Key key = new Key();
        key.setLogin(form.getLogin());
        key.setPassword(form.getPassword());
        key.setRole(form.getRole());
        keyService.create(key);
        Employee employee = new Employee();
        employee.setName(form.getName());
        employee.setReadingRoom(form.getReadingRoom());
        employee.setKey(key);
        employeeService.create(employee);
        return "redirect:/ui/users/show/" + employee.getId();
    }

}
