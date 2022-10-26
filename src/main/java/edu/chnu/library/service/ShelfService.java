package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.BookCase;
import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.model.Shelf;
import edu.chnu.library.repository.mongo.ShelfMongoRepository;
import edu.chnu.library.repository.sql.ShelfSqlRepository;
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
 * @date 16.08.2022 23:20
 * @class ShelfService
 */
@Service
public class ShelfService {
    ShelfSqlRepository shelfSqlRepository;
    ShelfMongoRepository shelfMongoRepository;

    @Autowired
    public ShelfService(ShelfSqlRepository shelfSqlRepository, ShelfMongoRepository shelfMongoRepository) {
        this.shelfSqlRepository = shelfSqlRepository;
        this.shelfMongoRepository = shelfMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Shelf create(Shelf shelf) {
        LocalDateTime time = LocalDateTime.now();
        shelf.setCreatedAt(time);
        shelf.setUpdatedAt(time);
        shelfMongoRepository.save(shelf);
        return shelfSqlRepository.save(shelf);
    }

    public Shelf get(String id) {
        return shelfSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Shelf update(Shelf shelf) {
        Shelf oldOne = get(shelf.getId());
        shelf.setCreatedAt(oldOne.getCreatedAt());
        shelf.setUpdatedAt(LocalDateTime.now());
        shelfMongoRepository.save(shelf);
        return shelfSqlRepository.save(shelf);
    }

    public void delete(String id) {
        shelfMongoRepository.deleteById(id);
        shelfSqlRepository.deleteById(id);
    }

    public List<Shelf> getAll() {
        return shelfSqlRepository.findAll();
    }

    public Page<Shelf> getByNumberContainingPaginated(String number, PageRequest pageRequest) {
        return shelfSqlRepository.findAllByNumberContainingIgnoreCase(number, pageRequest);
    }

    public List<Shelf> getByBookCase(BookCase bookCase) {
        return shelfSqlRepository.findAllByBookCase(bookCase);
    }

    public List<Shelf> getByReadingRoom(ReadingRoom readingRoom) {
        return shelfSqlRepository.findAllByBookCase_ReadingRoom(readingRoom);
    }

    public List<Shelf> getAllByNumberContaining(String number, Sort sort) {
        try {
            return shelfSqlRepository.findAllByNumberContainingIgnoreCase(number, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами number=" + number + "; sort=" + sort.toString());
        }
    }

    public List<Shelf> getAllByNumberContainingAndBetween(String number, String number2, String number3, Sort sort) {
        try {
            return shelfSqlRepository.findAllByNumberContainingIgnoreCaseAndNumberBetween(number, number2, number3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами number=" + number + "; sort=" + sort.toString() + "; range=[" + number2 + ":" + number3 + "]");
        }
    }

    public Page<Shelf> getAllPaginated(PageRequest pageRequest) {
        return shelfSqlRepository.findAll(pageRequest);
    }
}
