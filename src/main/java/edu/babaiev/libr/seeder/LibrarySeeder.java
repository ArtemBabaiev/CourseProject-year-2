package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.LibraryFactory;
import edu.babaiev.libr.model.Library;
import edu.babaiev.libr.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:47
 * @class LibrarySeeder
 */
@Component
public class LibrarySeeder {
    LibraryFactory factory;
    LibraryService service;
    @Autowired
    public LibrarySeeder(LibraryFactory factory, LibraryService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Library> list = factory.generate();
        for (Library item:list) {
            service.create(item);
        }
    }
}
