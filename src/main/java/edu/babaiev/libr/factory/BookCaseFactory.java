package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.BookCase;
import edu.babaiev.libr.model.ReadingRoom;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 00:39
 * @class BookCaseFactory
 */
@Component
public class BookCaseFactory {
    public List<BookCase> generate() {
        List<BookCase> bookCases = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.BOOK_CASE.getValue(); i++) {
            bookCases.add(
                    new BookCase(i + "",
                            faker.random().hex(2),
                            new ReadingRoom(faker.random().nextInt(1, QuantityConfig.READING_ROOM.getValue()) + ""),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return bookCases;
    }

}
