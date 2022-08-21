package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.EmployeeFactory;
import edu.babaiev.libr.model.Employee;
import edu.babaiev.libr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:46
 * @class EmployeeSeeder
 */
@Component
public class EmployeeSeeder {
    EmployeeFactory factory;
    EmployeeService service;
    @Autowired
    public EmployeeSeeder(EmployeeFactory factory, EmployeeService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Employee> list = factory.generate();
        for (Employee item:list) {
            service.create(item);
        }
    }
}
