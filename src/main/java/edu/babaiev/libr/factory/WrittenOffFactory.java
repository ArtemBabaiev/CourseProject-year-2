package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.Publisher;
import edu.babaiev.libr.model.WrittenOff;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:25
 * @class WrittenOffFactory
 */
@Component
public class WrittenOffFactory {
    public List<WrittenOff> generate() {
        List<WrittenOff> writtenOffs = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.WRITTEN_OFF.getValue(); i++) {
            writtenOffs.add(
                    new WrittenOff(i + "",
                            faker.internet().ipV4Address(),
                            faker.book().title(),
                            faker.random().nextInt(1990, 2022),
                            new Publisher(faker.random().nextInt(1, QuantityConfig.PUBLISHER.getValue()) + ""),
                            faker.random().nextInt(50),
                            faker.random().nextInt(50, 300),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            );
        }
        return writtenOffs;
    }
}
