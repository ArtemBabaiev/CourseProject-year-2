package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Collection;
import edu.babaiev.libr.repository.mongo.CollectionMongoRepository;
import edu.babaiev.libr.repository.sql.CollectionSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 22:53
 * @class CollectionService
 */
@Service
public class CollectionService {
    CollectionSqlRepository collectionSqlRepository;
    CollectionMongoRepository collectionMongoRepository;

    @Autowired
    public CollectionService(CollectionSqlRepository collectionSqlRepository, CollectionMongoRepository collectionMongoRepository) {
        this.collectionSqlRepository = collectionSqlRepository;
        this.collectionMongoRepository = collectionMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Collection create(Collection collection) {
        LocalDateTime time = LocalDateTime.now();
        collection.setCreated_at(time);
        collection.setUpdated_at(time);
        collectionMongoRepository.save(collection);
        return collectionSqlRepository.save(collection);
    }

    public Collection get(String id) {
        return collectionSqlRepository.findById(id).orElse(null);
    }

    public Collection update(Collection collection) {
        collection.setUpdated_at(LocalDateTime.now());
        collectionMongoRepository.save(collection);
        return collectionSqlRepository.save(collection);
    }

    public void delete(String id) {
        collectionMongoRepository.deleteById(id);
        collectionSqlRepository.deleteById(id);
    }

    public List<Collection> getAll() {
        return collectionSqlRepository.findAll();
    }
}
