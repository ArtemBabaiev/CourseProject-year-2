package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 04:00
 * @class StudentFactory
 */
@Component
public class StudentFactory {
    public List<Student> generate() {
        List<Student> students = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 3 * QuantityConfig.PUPIL.getValue() + 1; i <= 4 * QuantityConfig.SCIENTIST.getValue(); i++) {
            students.add(
                    new Student(i + "",
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.address().fullAddress(),
                            faker.internet().uuid(),
                            faker.phoneNumber().phoneNumber(),
                            LocalDate.now(),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            faker.university().name(),
                            faker.random().hex(8),
                            faker.random().nextInt(1, 5),
                            faker.random().hex(3)
                            )
            );
        }
        return students;
    }
}
