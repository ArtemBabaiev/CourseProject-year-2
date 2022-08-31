package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:28
 * @class RecordFactory
 */
@Component
public class RecordFactory {
    public List<Record> generate() {
        List<Record> records = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.RECORD.getValue(); i++) {
            records.add(
                    new Record(i + "",
                            faker.random().nextBoolean() ? new Student(faker.random().nextInt(16, 20) + "") :
                                    new Scientist(faker.random().nextInt(11, 15) + ""),
                            new Employee(faker.random().nextInt(1, QuantityConfig.EMPLOYEE.getValue()) + ""),
                            new Employee(faker.random().nextInt(1, QuantityConfig.EMPLOYEE.getValue()) + ""),
                            new Exemplar(faker.random().nextInt(1, QuantityConfig.EXEMPLAR.getValue()) + ""),
                            LocalDateTime.now(),
                            faker.random().nextBoolean()? LocalDateTime.now(): null,
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );

        }
        return records;
    }
}
