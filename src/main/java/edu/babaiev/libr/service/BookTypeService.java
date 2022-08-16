package edu.babaiev.libr.service;

import edu.babaiev.libr.model.BookType;
import edu.babaiev.libr.repository.mongo.BookTypeMongoRepository;
import edu.babaiev.libr.repository.sql.BookTypeSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 22:51
 * @class BookTypeService
 */
@Service
public class BookTypeService {
    BookTypeSqlRepository bookTypeSqlRepository;
    BookTypeMongoRepository bookTypeMongoRepository;

    @Autowired
    public BookTypeService(BookTypeSqlRepository bookTypeSqlRepository, BookTypeMongoRepository bookTypeMongoRepository) {
        this.bookTypeSqlRepository = bookTypeSqlRepository;
        this.bookTypeMongoRepository = bookTypeMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public BookType create(BookType bookType) {
        bookType.setCreated_at(LocalDateTime.now());
        bookTypeMongoRepository.save(bookType);
        return bookTypeSqlRepository.save(bookType);
    }

    public BookType get(String id) {
        return bookTypeSqlRepository.findById(id).orElse(null);
    }

    public BookType update(BookType bookType) {
        bookType.setUpdate_at(LocalDateTime.now());
        bookTypeMongoRepository.save(bookType);
        return bookTypeSqlRepository.save(bookType);
    }

    public void delete(String id) {
        bookTypeMongoRepository.deleteById(id);
        bookTypeSqlRepository.deleteById(id);
    }

    public List<BookType> getAll() {
        return bookTypeSqlRepository.findAll();
    }
}
