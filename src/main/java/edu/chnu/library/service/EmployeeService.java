package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Employee;
import edu.chnu.library.model.Key;
import edu.chnu.library.repository.mongo.EmployeeMongoRepository;
import edu.chnu.library.repository.sql.EmployeeSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return employeeSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
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

    public Page<Employee> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return employeeSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public Employee getByKey(Key key) {
        return employeeSqlRepository.findAllByKey(key);
    }

    @Transactional
    public List<Employee> getEmployeeByReadingRoom(String roomId) {
        return employeeSqlRepository.getEmployeesByReadingRoom(roomId);
    }

    public List<Employee> getAllByNameContaining(String name, Sort sort) {
        try {
            return employeeSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<Employee> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return employeeSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<Employee> getAllPaginated(PageRequest pageRequest) {
        return employeeSqlRepository.findAll(pageRequest);
    }
}
