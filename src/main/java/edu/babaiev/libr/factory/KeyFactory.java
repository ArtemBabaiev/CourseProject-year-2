package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.Key;
import edu.babaiev.libr.model.Role;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 02:30
 * @class KeyFactory
 */
@Component
public class KeyFactory {
    public List<Key> generate() {
        List<Key> keys = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.KEY.getValue(); i++) {
            keys.add(
                    new Key(i + "",
                            faker.internet().domainWord() + i,
                            "password",
                            new Role(faker.random().nextInt(1, 3) + ""),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return keys;
    }
}
