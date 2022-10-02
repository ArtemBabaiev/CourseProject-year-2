package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.Author;
import edu.babaiev.libr.model.Monograph;
import edu.babaiev.libr.model.MonographType;
import edu.babaiev.libr.model.Publisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 02:35
 * @class MonographFactory
 */
@Component
public class MonographFactory {
    public List<Monograph> generate() {
        List<Monograph> monographs = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 2 * QuantityConfig.COLLECTION.getValue() + 1; i <= 3 * QuantityConfig.MONOGRAPH.getValue(); i++) {
            monographs.add(
                    new Monograph(i + "",
                            faker.internet().ipV4Address(),
                            faker.book().title(),
                            faker.random().nextInt(1990, 2022),
                            new Publisher(faker.random().nextInt(1, QuantityConfig.PUBLISHER.getValue()) + ""),
                            faker.random().nextBoolean(),
                            faker.random().nextInt(50, 300),
                            faker.random().nextInt(0, 31),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            new Author(faker.random().nextInt(1, QuantityConfig.AUTHOR.getValue())+""),
                            new MonographType(faker.random().nextInt(1, QuantityConfig.MONOGRAPH_TYPE.getValue())+""),
                            faker.company().bs(),
                            faker.address().cityName()
                    )
            );
        }
        return monographs;
    }
}
