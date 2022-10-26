package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Collection;
import edu.chnu.library.repository.mongo.CollectionMongoRepository;
import edu.chnu.library.repository.sql.CollectionSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @collection artem
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
        collection.setCreatedAt(time);
        collection.setUpdatedAt(time);
        collectionMongoRepository.save(collection);
        return collectionSqlRepository.save(collection);
    }

    public Collection get(String id) {
        return collectionSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Collection update(Collection collection) {
        Collection oldOne = get(collection.getId());
        collection.setCreatedAt(oldOne.getCreatedAt());
        collection.setUpdatedAt(LocalDateTime.now());
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

    public Page<Collection> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return collectionSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public List<Collection> getAllByNameContaining(String name, Sort sort) {
        try {
            return collectionSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<Collection> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return collectionSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<Collection> getAllPaginated(PageRequest pageRequest) {
        return collectionSqlRepository.findAll(pageRequest);
    }
}
