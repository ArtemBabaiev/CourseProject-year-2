package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Book;
import edu.babaiev.libr.repository.mongo.BookMongoRepository;
import edu.babaiev.libr.repository.sql.BookSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 22:48
 * @class BookService
 */
@Service
public class BookService {
    BookSqlRepository bookSqlRepository;
    BookMongoRepository bookMongoRepository;

    @Autowired
    public BookService(BookSqlRepository bookSqlRepository, BookMongoRepository bookMongoRepository) {
        this.bookSqlRepository = bookSqlRepository;
        this.bookMongoRepository = bookMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Book create(Book book) {
        book.setCreated_at(LocalDateTime.now());
        bookMongoRepository.save(book);
        return bookSqlRepository.save(book);
    }

    public Book get(String id) {
        return bookSqlRepository.findById(id).orElse(null);
    }

    public Book update(Book book) {
        book.setUpdate_at(LocalDateTime.now());
        bookMongoRepository.save(book);
        return bookSqlRepository.save(book);
    }

    public void delete(String id) {
        bookMongoRepository.deleteById(id);
        bookSqlRepository.deleteById(id);
    }

    public List<Book> getAll() {
        return bookSqlRepository.findAll();
    }
}