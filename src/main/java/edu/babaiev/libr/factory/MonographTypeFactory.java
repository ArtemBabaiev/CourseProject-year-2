package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.MonographType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 02:56
 * @class MonographTypeFactory
 */
@Component
public class MonographTypeFactory {
    List<String> names = Arrays.asList("Реферат", "Дисертація");

    public List<MonographType> generate() {
        List<MonographType> monographTypes = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < names.size(); i++) {
            monographTypes.add(
                    new MonographType((i + 1) + "",
                            names.get(i),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return monographTypes;
    }
}
