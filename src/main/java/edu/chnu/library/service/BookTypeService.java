package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.BookType;
import edu.chnu.library.model.BookType;
import edu.chnu.library.repository.mongo.BookTypeMongoRepository;
import edu.chnu.library.repository.sql.BookTypeSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        LocalDateTime time = LocalDateTime.now();
        bookType.setCreatedAt(time);
        bookType.setUpdatedAt(time);
        bookTypeMongoRepository.save(bookType);
        return bookTypeSqlRepository.save(bookType);
    }

    public BookType get(String id) {
        return bookTypeSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public BookType update(BookType bookType) {
        BookType oldOne = get(bookType.getId());
        bookType.setCreatedAt(oldOne.getCreatedAt());
        bookType.setUpdatedAt(LocalDateTime.now());
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

    public Page<BookType> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return bookTypeSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public List<BookType> getAllByNameContaining(String name, Sort sort) {
        try {
            return bookTypeSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<BookType> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return bookTypeSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<BookType> getAllPaginated(PageRequest pageRequest){
        return bookTypeSqlRepository.findAll(pageRequest);
    }
}
