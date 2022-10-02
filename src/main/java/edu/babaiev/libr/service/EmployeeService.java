package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Employee;
import edu.babaiev.libr.repository.mongo.EmployeeMongoRepository;
import edu.babaiev.libr.repository.sql.EmployeeSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 22:56
 * @class EmployeeService
 */
@Service
public class EmployeeService {
    EmployeeSqlRepository employeeSqlRepository;
    EmployeeMongoRepository employeeMongoRepository;

    @Autowired
    public EmployeeService(EmployeeSqlRepository employeeSqlRepository, EmployeeMongoRepository employeeMongoRepository) {
        this.employeeSqlRepository = employeeSqlRepository;
        this.employeeMongoRepository = employeeMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Employee create(Employee employee) {
        LocalDateTime time = LocalDateTime.now();
        employee.setCreatedAt(time);
        employee.setUpdatedAt(time);
        employeeMongoRepository.save(employee);
        return employeeSqlRepository.save(employee);
    }

    public Employee get(String id) {
        return employeeSqlRepository.findById(id).orElse(null);
    }

    public Employee update(Employee employee) {
        Employee oldOne = get(employee.getId());
        employee.setCreatedAt(oldOne.getCreatedAt());
        employee.setUpdatedAt(LocalDateTime.now());
        employeeMongoRepository.save(employee);
        return employeeSqlRepository.save(employee);
    }

    public void delete(String id) {
        employeeMongoRepository.deleteById(id);
        employeeSqlRepository.deleteById(id);
    }

    public List<Employee> getAll() {
        return employeeSqlRepository.findAll();
    }
    public Page<Employee> getByNameContainingPaginated(String name, PageRequest pageRequest){
        return employeeSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }
}
