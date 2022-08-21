package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.Author;
import edu.babaiev.libr.model.Publisher;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:10
 * @class PublisherFactory
 */
@Component
public class PublisherFactory {
    public List<Publisher> generate() {
        List<Publisher> publishers = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.PUBLISHER.getValue(); i++) {
            publishers.add(
                    new Publisher(i+"",
                            faker.book().publisher(),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return publishers;
    }
}
