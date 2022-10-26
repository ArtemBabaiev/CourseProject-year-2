package edu.chnu.library.security;

import edu.chnu.library.model.Employee;
import edu.chnu.library.model.Key;
import edu.chnu.library.service.EmployeeService;
import edu.chnu.library.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 09.10.2022 21:22
 * @class AuthService
 */
@Service
public class AuthService {
    EmployeeService employeeService;
    KeyService keyService;

    @Autowired
    public AuthService(EmployeeService employeeService, KeyService keyService) {
        this.employeeService = employeeService;
        this.keyService = keyService;
    }

    public Employee getCurrentEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Key key = ((MyUserDetails) auth.getPrincipal()).getKey();
        return employeeService.getByKey(key);
    }
}
