package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.Genre;
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
 * @date 20.08.2022 02:13
 * @class GenreFactory
 */
@Component
public class GenreFactory {
    public List<String> names = Arrays.asList("Класика", "Трагедія", "Наукова фантастика", "Фантазія", "Казка");

    public List<Genre> generate() {
        List<Genre> genres = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < names.size(); i++) {
            genres.add(
                    new Genre((i + 1) + "",
                            names.get(i),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return genres;
    }
}
