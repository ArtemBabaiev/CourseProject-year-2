package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.Employee;
import edu.babaiev.libr.model.Key;
import edu.babaiev.libr.model.ReadingRoom;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 02:09
 * @class EmployeeFactory
 */
@Component
public class EmployeeFactory {
    public List<Employee> generate() {
        List<Employee> employees = new ArrayList<>();
        Faker faker = new Faker();
        int key_id = 1;
        for (int i = 1; i <= QuantityConfig.EMPLOYEE.getValue(); i++) {
            employees.add(
                    new Employee(i + "",
                            faker.name().fullName(),
                            new ReadingRoom(faker.random().nextInt(1, QuantityConfig.READING_ROOM.getValue())+""),
                            new Key(key_id+""),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
            key_id++;
        }
        return employees;
    }
}
