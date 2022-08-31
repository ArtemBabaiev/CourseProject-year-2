package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.CollectionType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 02:04
 * @class CollectionTypeFactory
 */
@Component
public class CollectionTypeFactory {
    public List<String> names = Arrays.asList("Збірник віршів", "Збірник казок", "Збірник доповідей");

    public List<CollectionType> generate() {
        List<CollectionType> collectionTypes = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < names.size(); i++) {
            collectionTypes.add(
                    new CollectionType((i + 1) + "",
                            names.get(i),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return collectionTypes;
    }
}
