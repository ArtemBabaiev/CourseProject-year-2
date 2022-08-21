package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.BookCaseFactory;
import edu.babaiev.libr.model.BookCase;
import edu.babaiev.libr.service.BookCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:44
 * @class BookCaseSeeder
 */
@Component
public class BookCaseSeeder {
    BookCaseFactory factory;
    BookCaseService service;
    @Autowired
    public BookCaseSeeder(BookCaseFactory factory, BookCaseService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<BookCase> list = factory.generate();
        for (BookCase item:list) {
            service.create(item);
        }
    }
}
