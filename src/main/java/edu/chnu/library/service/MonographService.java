package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Monograph;
import edu.chnu.library.repository.mongo.MonographMongoRepository;
import edu.chnu.library.repository.sql.MonographSqlRepository;
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
 * @date 16.08.2022 23:05
 * @class MonographService
 */
@Service
public class MonographService {
    MonographSqlRepository monographSqlRepository;
    MonographMongoRepository monographMongoRepository;

    @Autowired
    public MonographService(MonographSqlRepository monographSqlRepository, MonographMongoRepository monographMongoRepository) {
        this.monographSqlRepository = monographSqlRepository;
        this.monographMongoRepository = monographMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Monograph create(Monograph monograph) {
        LocalDateTime time = LocalDateTime.now();
        monograph.setCreatedAt(time);
        monograph.setUpdatedAt(time);
        monographMongoRepository.save(monograph);
        return monographSqlRepository.save(monograph);
    }

    public Monograph get(String id) {
        return monographSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Monograph update(Monograph monograph) {
        Monograph oldOne = get(monograph.getId());
        monograph.setCreatedAt(oldOne.getCreatedAt());
        monograph.setUpdatedAt(LocalDateTime.now());
        monographMongoRepository.save(monograph);
        return monographSqlRepository.save(monograph);
    }

    public void delete(String id) {
        monographMongoRepository.deleteById(id);
        monographSqlRepository.deleteById(id);
    }

    public List<Monograph> getAll() {
        return monographSqlRepository.findAll();
    }

    public Page<Monograph> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return monographSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public List<Monograph> getAllByNameContaining(String name, Sort sort) {
        try {
            return monographSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<Monograph> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return monographSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<Monograph> getAllPaginated(PageRequest pageRequest) {
        return monographSqlRepository.findAll(pageRequest);
    }
}
