package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.Library;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 02:33
 * @class LibraryFactory
 */
@Component
public class LibraryFactory {
    public List<Library> generate() {
        List<Library> libraries = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.LIBRARY.getValue(); i++) {
            libraries.add(
                    new Library(i + "",
                            faker.random().hex(2),
                            faker.address().fullAddress(),
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
        }
        return libraries;
    }
}
