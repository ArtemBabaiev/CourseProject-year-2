package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.Author;
import edu.babaiev.libr.model.Writing;
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
 * @date 20.08.2022 03:23
 * @class WritingFactory
 */
@Component
public class WritingFactory {
    public List<Writing> generate() {
        List<Writing> writings = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.WRITING.getValue(); i++) {
            writings.add(
                    new Writing(i + "",
                            faker.book().title(),
                            new HashSet<>(
                                    Arrays.asList(
                                            new Author(faker.random().nextInt(1, QuantityConfig.AUTHOR.getValue()) + ""),
                                            new Author(faker.random().nextInt(1, QuantityConfig.AUTHOR.getValue()) + "")
                                    )
                            ),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
        }
        return writings;
    }
}
