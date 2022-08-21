package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.GenreFactory;
import edu.babaiev.libr.model.Genre;
import edu.babaiev.libr.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:46
 * @class GenreSeeder
 */
@Component
public class GenreSeeder {
    GenreFactory factory;
    GenreService service;
    @Autowired
    public GenreSeeder(GenreFactory factory, GenreService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Genre> list = factory.generate();
        for (Genre item:list) {
            service.create(item);
        }
    }
}
