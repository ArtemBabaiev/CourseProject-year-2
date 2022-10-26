package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Employee;
import edu.chnu.library.model.Key;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:29
 * @class EmployeeSqlRepository
 */
public interface EmployeeSqlRepository extends JpaRepository<Employee, String> {
    Page<Employee> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Employee> findAllByNameContainingIgnoreCase(String name, Sort sort);

    @Procedure("getEmployeesByReadingRoom")
    List<Employee> getEmployeesByReadingRoom(String roomId);


    Employee findAllByKey(Key key);

    List<Employee> findAllByNameContainingIgnoreCaseAndNameBetween(String name, String name2, String name3, Sort sort);

}
