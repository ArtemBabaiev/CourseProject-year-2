package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.BookType;
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
 * @date 20.08.2022 00:59
 * @class BookTypeFactory
 */
@Component
public class BookTypeFactory {
    List<String> names = Arrays.asList("Художня", "Довідник", "Підручник", "Енциклопедія", "Словник");

    public List<BookType> generate() {
        List<BookType> bookTypes = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < names.size(); i++) {
            bookTypes.add(
                    new BookType((i + 1) + "",
                            names.get(i),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return bookTypes;
    }
}
