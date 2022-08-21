package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.Role;
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
 * @date 20.08.2022 03:16
 * @class RoleFactory
 */
@Component
public class RoleFactory {
    List<String> names = Arrays.asList("OWNER", "ADMIN", "OPERATOR");

    public List<Role> generate() {
        List<Role> roles = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < names.size(); i++) {
            roles.add(
                    new Role((i + 1) + "",
                            names.get(i),
                            faker.lorem().toString(),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return roles;
    }
}
