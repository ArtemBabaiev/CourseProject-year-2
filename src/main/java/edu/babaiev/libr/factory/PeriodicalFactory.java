package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:04
 * @class PeriodicalFactory
 */
@Component
public class PeriodicalFactory {
    public List<Periodical> generate() {
        List<Periodical> periodicals = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 3 * QuantityConfig.MONOGRAPH.getValue() + 1; i <= 4 * QuantityConfig.PERIODICAL.getValue(); i++) {
            periodicals.add(new Periodical(i + "",
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
                            faker.random().nextInt(12) + "/12",
                            new Subject(faker.random().nextInt(1, QuantityConfig.SUBJECT.getValue()) + ""),
                            new PeriodicalType(faker.random().nextInt(1, QuantityConfig.PERIODICAL_TYPE.getValue()) + ""),
                            new HashSet<Article>(
                                    Arrays.asList(
                                            new Article(faker.random().nextInt(1, QuantityConfig.ARTICLE.getValue()) + ""),
                                            new Article(faker.random().nextInt(1, QuantityConfig.ARTICLE.getValue()) + ""),
                                            new Article(faker.random().nextInt(1, QuantityConfig.ARTICLE.getValue()) + "")
                                    )
                            )
                    )
            );
        }
        return periodicals;
    }
}
