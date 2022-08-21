package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.PeriodicalType;
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
 * @date 20.08.2022 03:09
 * @class PeriodicalTypeFactory
 */
@Component
public class PeriodicalTypeFactory {
    List<String> names = Arrays.asList("Журнал", "Газета");

    public List<PeriodicalType> generate() {
        List<PeriodicalType> periodicalTypes = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < names.size(); i++) {
            periodicalTypes.add(
                    new PeriodicalType((i + 1) + "",
                            names.get(i),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return periodicalTypes;
    }
}
