package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.Author;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 19.08.2022 23:28
 * @class AuthorFactory
 */
@Component
public class AuthorFactory {
    public List<Author> generate() {
        List<Author> authors = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.AUTHOR.getValue(); i++) {
            authors.add(
                    new Author(i +"",
                            faker.book().author(),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return authors;
    }
}
