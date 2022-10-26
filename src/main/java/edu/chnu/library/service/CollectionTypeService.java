package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Collection;
import edu.chnu.library.model.CollectionType;
import edu.chnu.library.repository.mongo.CollectionTypeMongoRepository;
import edu.chnu.library.repository.sql.CollectionTypeSqlRepository;
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
        LocalDateTime time = LocalDateTime.now();
        collectionType.setCreatedAt(time);
        collectionType.setUpdatedAt(time);
        collectionTypeMongoRepository.save(collectionType);
        return collectionTypeSqlRepository.save(collectionType);
    }

    public CollectionType get(String id) {
        return collectionTypeSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public CollectionType update(CollectionType collectionType) {
        CollectionType oldOne = get(collectionType.getId());
        collectionType.setCreatedAt(oldOne.getCreatedAt());
        collectionType.setUpdatedAt(LocalDateTime.now());
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

    public Page<CollectionType> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return collectionTypeSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public List<CollectionType> getAllByNameContaining(String name, Sort sort) {
        try {
            return collectionTypeSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<CollectionType> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return collectionTypeSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<CollectionType> getAllPaginated(PageRequest pageRequest){
        return collectionTypeSqlRepository.findAll(pageRequest);
    }
}
