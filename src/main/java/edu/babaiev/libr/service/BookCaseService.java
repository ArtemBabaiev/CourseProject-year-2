package edu.babaiev.libr.service;

import edu.babaiev.libr.model.BookCase;
import edu.babaiev.libr.model.ReadingRoom;
import edu.babaiev.libr.repository.mongo.BookCaseMongoRepository;
import edu.babaiev.libr.repository.sql.BookCaseSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 22:47
 * @class BookCaseService
 */
@Service
public class BookCaseService {
    BookCaseSqlRepository bookCaseSqlRepository;
    BookCaseMongoRepository bookCaseMongoRepository;

    @Autowired
    public BookCaseService(BookCaseSqlRepository bookCaseSqlRepository, BookCaseMongoRepository bookCaseMongoRepository) {
        this.bookCaseSqlRepository = bookCaseSqlRepository;
        this.bookCaseMongoRepository = bookCaseMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public BookCase create(BookCase bookCase) {
        LocalDateTime time = LocalDateTime.now();
        bookCase.setCreatedAt(time);
        bookCase.setUpdatedAt(time);
        bookCaseMongoRepository.save(bookCase);
        return bookCaseSqlRepository.save(bookCase);
    }

    public BookCase get(String id) {
        return bookCaseSqlRepository.findById(id).orElse(null);
    }

    public BookCase update(BookCase bookCase) {
        BookCase oldOne = get(bookCase.getId());
        bookCase.setCreatedAt(oldOne.getCreatedAt());
        bookCase.setUpdatedAt(LocalDateTime.now());
        bookCaseMongoRepository.save(bookCase);
        return bookCaseSqlRepository.save(bookCase);
    }

    public void delete(String id) {
        bookCaseMongoRepository.deleteById(id);
        bookCaseSqlRepository.deleteById(id);
    }

    public List<BookCase> getAll() {
        return bookCaseSqlRepository.findAll();
    }

    public Page<BookCase> getByNumberContainingPaginated(String number, PageRequest pageRequest){
        return bookCaseSqlRepository.findAllByNumberContainingIgnoreCase(number, pageRequest);
    }
    public List<BookCase> getByReadingRoom(ReadingRoom readingRoom){
        return bookCaseSqlRepository.findAllByReadingRoom(readingRoom);
    }
}
