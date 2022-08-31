package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.BookCase;
import edu.babaiev.libr.model.Shelf;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:19
 * @class ShelfFactory
 */
@Component
public class ShelfFactory {
    public List<Shelf> generate() {
        List<Shelf> shelves = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.SHELF.getValue(); i++) {
            shelves.add(
                    new Shelf(i + "",
                            faker.random().hex(2),
                            new BookCase(faker.random().nextInt(1, QuantityConfig.BOOK_CASE.getValue()) + ""),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return shelves;
    }
}
