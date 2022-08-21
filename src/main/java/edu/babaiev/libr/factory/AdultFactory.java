package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.Adult;
import edu.babaiev.libr.model.Key;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 19.08.2022 23:00
 * @class AdultFactory
 */
@Component
public class AdultFactory {
    public List<Adult> generate() {
        List<Adult> adults = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.ADULT.getValue(); i++) {
            adults.add(
                    new Adult(i + "",
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.address().fullAddress(),
                            faker.internet().uuid(),
                            faker.phoneNumber().phoneNumber(),
                            LocalDate.now(),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            faker.company().name()
                    )
            );
        }
        return adults;
    }
}
