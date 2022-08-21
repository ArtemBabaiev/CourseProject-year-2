package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.Key;
import edu.babaiev.libr.model.Scientist;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:17
 * @class ScientistFactory
 */
@Component
public class ScientistFactory {
    public List<Scientist> generate() {
        List<Scientist> scientists = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 2 * QuantityConfig.PUPIL.getValue() + 1; i <= 3 * QuantityConfig.SCIENTIST.getValue(); i++) {
            scientists.add(
                    new Scientist(i + "",
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.address().fullAddress(),
                            faker.internet().uuid(),
                            faker.phoneNumber().phoneNumber(),
                            LocalDate.now(),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            faker.company().name(),
                            faker.company().profession()
                    )
            );
        }
        return scientists;
    }
}
