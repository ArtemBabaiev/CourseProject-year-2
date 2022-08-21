package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.*;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 00:27
 * @class BookFactory
 */
@Component
public class BookFactory {
    public List<Book> generate() {
        List<Book> books = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.BOOK.getValue(); i++) {
            books.add(new Book(i + "",
                    faker.internet().ipV4Address(),
                    faker.book().title(),
                    faker.random().nextInt(1990, 2022),
                    new Publisher(faker.random().nextInt(1, QuantityConfig.PUBLISHER.getValue()) + ""),
                    new Shelf(faker.random().nextInt(1, QuantityConfig.SHELF.getValue()) + ""),
                    faker.random().nextInt(10),
                    faker.random().nextBoolean(),
                    faker.random().nextInt(50, 300),
                    faker.random().nextInt(0, 31),
                    faker.lorem().toString(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    new Genre(faker.random().nextInt(1, QuantityConfig.GENRE.getValue()) + ""),
                    new BookType(faker.random().nextInt(1, QuantityConfig.BOOK_TYPE.getValue()) + ""),
                    new Writing(faker.random().nextInt(1, QuantityConfig.WRITING.getValue()) + "")));
        }
        return books;
    }

}
