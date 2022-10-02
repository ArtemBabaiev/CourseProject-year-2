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
 * @date 20.08.2022 01:10
 * @class CollectionFactory
 */
@Component
public class CollectionFactory {
    public List<Collection> generate() {
        List<Collection> collections = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = QuantityConfig.BOOK.getValue() + 1; i <= 2 * QuantityConfig.COLLECTION.getValue(); i++) {
            collections.add(
                    new Collection(i + "",
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
                            new Genre(faker.random().nextInt(1, QuantityConfig.GENRE.getValue()) + ""),
                            new CollectionType(faker.random().nextInt(1, QuantityConfig.COLLECTION_TYPE.getValue()) + ""),
                            new HashSet<Writing>(
                                    Arrays.asList(
                                            new Writing(faker.random().nextInt(1, QuantityConfig.WRITING.getValue()) + ""),
                                            new Writing(faker.random().nextInt(1, QuantityConfig.WRITING.getValue()) + "")
                                    )
                            )
                    )
            );
        }
        return collections;
    }
}
