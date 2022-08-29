package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Shelf;
import edu.babaiev.libr.repository.mongo.ShelfMongoRepository;
import edu.babaiev.libr.repository.sql.ShelfSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        shelf.setCreated_at(time);
        shelf.setUpdated_at(time);
        shelfMongoRepository.save(shelf);
        return shelfSqlRepository.save(shelf);
    }

    public Shelf get(String id) {
        return shelfSqlRepository.findById(id).orElse(null);
    }

    public Shelf update(Shelf shelf) {
        shelf.setUpdated_at(LocalDateTime.now());
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
}
