package edu.babaiev.libr.service;

import edu.babaiev.libr.model.CollectionType;
import edu.babaiev.libr.repository.mongo.CollectionTypeMongoRepository;
import edu.babaiev.libr.repository.sql.CollectionTypeSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 22:54
 * @class CollectionTypeService
 */
@Service
public class CollectionTypeService {
    CollectionTypeSqlRepository collectionTypeSqlRepository;
    CollectionTypeMongoRepository collectionTypeMongoRepository;

    @Autowired
    public CollectionTypeService(CollectionTypeSqlRepository collectionTypeSqlRepository, CollectionTypeMongoRepository collectionTypeMongoRepository) {
        this.collectionTypeSqlRepository = collectionTypeSqlRepository;
        this.collectionTypeMongoRepository = collectionTypeMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public CollectionType create(CollectionType collectionType) {
        collectionType.setCreated_at(LocalDateTime.now());
        collectionTypeMongoRepository.save(collectionType);
        return collectionTypeSqlRepository.save(collectionType);
    }

    public CollectionType get(String id) {
        return collectionTypeSqlRepository.findById(id).orElse(null);
    }

    public CollectionType update(CollectionType collectionType) {
        collectionType.setUpdate_at(LocalDateTime.now());
        collectionTypeMongoRepository.save(collectionType);
        return collectionTypeSqlRepository.save(collectionType);
    }

    public void delete(String id) {
        collectionTypeMongoRepository.deleteById(id);
        collectionTypeSqlRepository.deleteById(id);
    }

    public List<CollectionType> getAll() {
        return collectionTypeSqlRepository.findAll();
    }
}
