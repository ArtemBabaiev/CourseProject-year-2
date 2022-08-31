package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Monograph;
import edu.babaiev.libr.repository.mongo.MonographMongoRepository;
import edu.babaiev.libr.repository.sql.MonographSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        monograph.setCreated_at(time);
        monograph.setUpdated_at(time);
        monographMongoRepository.save(monograph);
        return monographSqlRepository.save(monograph);
    }

    public Monograph get(String id) {
        return monographSqlRepository.findById(id).orElse(null);
    }

    public Monograph update(Monograph monograph) {
        Monograph oldOne = get(monograph.getId());
        monograph.setCreated_at(oldOne.getCreated_at());
        monograph.setUpdated_at(LocalDateTime.now());
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
}
