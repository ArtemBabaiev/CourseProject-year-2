package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.Subject;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:20
 * @class SubjectFactory
 */
@Component
public class SubjectFactory {
    List<String> names = Arrays.asList("Юудівельниц", "Сад, город", "Науквовий", "Дитячий", "Спорт");

    public List<Subject> generate() {
        List<Subject> subjects = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < names.size(); i++) {
            subjects.add(
                    new Subject((i + 1) + "",
                            names.get(i),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return subjects;
    }
}