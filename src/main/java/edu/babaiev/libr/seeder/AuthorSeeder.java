package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.AuthorFactory;
import edu.babaiev.libr.model.Author;
import edu.babaiev.libr.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:42
 * @class AuthorSeeder
 */
@Component
public class AuthorSeeder {
    AuthorFactory factory;
    AuthorService service;
    @Autowired
    public AuthorSeeder(AuthorFactory factory, AuthorService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Author> list = factory.generate();
        for (Author item:list) {
            service.create(item);
        }
    }
}
