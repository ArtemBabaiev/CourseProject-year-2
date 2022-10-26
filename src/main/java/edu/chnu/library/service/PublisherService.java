package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Publisher;
import edu.chnu.library.repository.mongo.PublisherMongoRepository;
import edu.chnu.library.repository.sql.PublisherSqlRepository;
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
 * @date 16.08.2022 23:11
 * @class PublisherService
 */
@Service
public class PublisherService {
    PublisherSqlRepository publisherSqlRepository;
    PublisherMongoRepository publisherMongoRepository;

    @Autowired
    public PublisherService(PublisherSqlRepository publisherSqlRepository, PublisherMongoRepository publisherMongoRepository) {
        this.publisherSqlRepository = publisherSqlRepository;
        this.publisherMongoRepository = publisherMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Publisher create(Publisher publisher) {
        LocalDateTime time = LocalDateTime.now();
        publisher.setCreatedAt(time);
        publisher.setUpdatedAt(time);
        publisherMongoRepository.save(publisher);
        return publisherSqlRepository.save(publisher);
    }

    public Publisher get(String id) {
        return publisherSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Publisher update(Publisher publisher) {
        Publisher oldOne = get(publisher.getId());
        publisher.setCreatedAt(oldOne.getCreatedAt());
        publisher.setUpdatedAt(LocalDateTime.now());
        publisherMongoRepository.save(publisher);
        return publisherSqlRepository.save(publisher);
    }

    public void delete(String id) {
        publisherMongoRepository.deleteById(id);
        publisherSqlRepository.deleteById(id);
    }

    public List<Publisher> getAll() {
        return publisherSqlRepository.findAll();
    }

    public Page<Publisher> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return publisherSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public List<Publisher> getAllByNameContaining(String name, Sort sort) {
        try {
            return publisherSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<Publisher> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return publisherSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<Publisher> getAllPaginated(PageRequest pageRequest) {
        return publisherSqlRepository.findAll(pageRequest);
    }
}
