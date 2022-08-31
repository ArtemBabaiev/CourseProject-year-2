package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.Pupil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:11
 * @class PupilFactory
 */
@Component
public class PupilFactory {
    public List<Pupil> generate() {
        List<Pupil> pupils = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = QuantityConfig.ADULT.getValue() + 1; i <= 2 * QuantityConfig.PUPIL.getValue(); i++) {
            pupils.add(
                    new Pupil(i + "",
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.address().fullAddress(),
                            faker.internet().uuid(),
                            faker.phoneNumber().phoneNumber(),
                            LocalDate.now(),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            faker.university().name(),
                            faker.random().nextInt(1, 11) + "-A"
                    )
            );
        }
        return pupils;
    }
}
