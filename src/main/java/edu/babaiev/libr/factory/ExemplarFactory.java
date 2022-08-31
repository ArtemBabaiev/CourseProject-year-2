package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 31.08.2022 17:50
 * @class ExemplarFactory
 */
@Component
public class ExemplarFactory {
    public List<Exemplar> generate() {
        List<Exemplar> exemplars = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.EXEMPLAR.getValue(); i++) {
            exemplars.add(
                    new Exemplar(i + "",
                            faker.random().nextBoolean(),
                            new Shelf(faker.random().nextInt(1, QuantityConfig.SHELF.getValue()) + ""),
                            faker.random().nextBoolean() ? new Book(faker.random().nextInt(1, QuantityConfig.BOOK.getValue()) + "") :
                                    faker.random().nextBoolean() ? new Collection(faker.random().nextInt(1, QuantityConfig.COLLECTION.getValue()) + "") :
                                            faker.random().nextBoolean() ? new Monograph(faker.random().nextInt(1, QuantityConfig.MONOGRAPH.getValue()) + "") :
                                                    new Periodical(faker.random().nextInt(1, QuantityConfig.PERIODICAL.getValue()) + ""),
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
        }
        return exemplars;
    }
}
