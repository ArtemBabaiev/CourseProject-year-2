package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Scientist;
import edu.babaiev.libr.repository.mongo.ScientistMongoRepository;
import edu.babaiev.libr.repository.sql.ScientistSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 19.08.2022 22:51
 * @class ScientistService
 */
@Service
public class ScientistService {
    ScientistSqlRepository scientistSqlRepository;
    ScientistMongoRepository scientistMongoRepository;

    @Autowired
    public ScientistService(ScientistSqlRepository scientistSqlRepository, ScientistMongoRepository scientistMongoRepository) {
        this.scientistSqlRepository = scientistSqlRepository;
        this.scientistMongoRepository = scientistMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Scientist create(Scientist scientist) {
        scientist.setCreated_at(LocalDateTime.now());
        scientistMongoRepository.save(scientist);
        return scientistSqlRepository.save(scientist);
    }

    public Scientist get(String id) {
        return scientistSqlRepository.findById(id).orElse(null);
    }

    public Scientist update(Scientist scientist) {
        scientist.setUpdate_at(LocalDateTime.now());
        scientistMongoRepository.save(scientist);
        return scientistSqlRepository.save(scientist);
    }

    public void delete(String id) {
        scientistMongoRepository.deleteById(id);
        scientistSqlRepository.deleteById(id);
    }

    public List<Scientist> getAll() {
        return scientistSqlRepository.findAll();
    }
}