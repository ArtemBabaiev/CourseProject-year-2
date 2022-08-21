package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.BookTypeFactory;
import edu.babaiev.libr.model.BookType;
import edu.babaiev.libr.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:45
 * @class BookTypeSeeder
 */
@Component
public class BookTypeSeeder {
    BookTypeFactory factory;
    BookTypeService service;
    @Autowired
    public BookTypeSeeder(BookTypeFactory factory, BookTypeService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<BookType> list = factory.generate();
        for (BookType item:list) {
            service.create(item);
        }
    }
}
