package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.BookFactory;
import edu.babaiev.libr.factory.CollectionFactory;
import edu.babaiev.libr.factory.MonographFactory;
import edu.babaiev.libr.factory.PeriodicalFactory;
import edu.babaiev.libr.model.*;
import edu.babaiev.libr.service.BookService;
import edu.babaiev.libr.service.CollectionService;
import edu.babaiev.libr.service.MonographService;
import edu.babaiev.libr.service.PeriodicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:53
 * @class LiteratureSeeder
 */
@Component
public class LiteratureSeeder {
    BookService bookService;
    BookFactory bookFactory;

    CollectionService collectionService;
    CollectionFactory collectionFactory;

    MonographService monographService;
    MonographFactory monographFactory;

    PeriodicalService periodicalService;
    PeriodicalFactory periodicalFactory;

    @Autowired
    public LiteratureSeeder(BookService bookService, BookFactory bookFactory, CollectionService collectionService, CollectionFactory collectionFactory, MonographService monographService, MonographFactory monographFactory, PeriodicalService periodicalService, PeriodicalFactory periodicalFactory) {
        this.bookService = bookService;
        this.bookFactory = bookFactory;
        this.collectionService = collectionService;
        this.collectionFactory = collectionFactory;
        this.monographService = monographService;
        this.monographFactory = monographFactory;
        this.periodicalService = periodicalService;
        this.periodicalFactory = periodicalFactory;
    }

    public void Seed(){
        List<Book> listB = bookFactory.generate();
        for (Book item:listB) {
            bookService.create(item);
        }

        List<Collection> listC = collectionFactory.generate();
        for (Collection item:listC) {
            collectionService.create(item);
        }

        List<Monograph> listM = monographFactory.generate();
        for (Monograph item:listM) {
            monographService.create(item);
        }

        List<Periodical> listP = periodicalFactory.generate();
        for (Periodical item:listP) {
            periodicalService.create(item);
        }
    }
}
