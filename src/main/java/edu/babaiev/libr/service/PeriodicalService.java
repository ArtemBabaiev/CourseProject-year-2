package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Periodical;
import edu.babaiev.libr.repository.mongo.PeriodicalMongoRepository;
import edu.babaiev.libr.repository.sql.PeriodicalSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:09
 * @class PeriodicalService
 */
@Service
public class PeriodicalService {
    PeriodicalSqlRepository periodicalSqlRepository;
    PeriodicalMongoRepository periodicalMongoRepository;

    @Autowired
    public PeriodicalService(PeriodicalSqlRepository periodicalSqlRepository, PeriodicalMongoRepository periodicalMongoRepository) {
        this.periodicalSqlRepository = periodicalSqlRepository;
        this.periodicalMongoRepository = periodicalMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Periodical create(Periodical periodical) {
        LocalDateTime time = LocalDateTime.now();
        periodical.setCreated_at(time);
        periodical.setUpdated_at(time);
        periodicalMongoRepository.save(periodical);
        return periodicalSqlRepository.save(periodical);
    }

    public Periodical get(String id) {
        return periodicalSqlRepository.findById(id).orElse(null);
    }

    public Periodical update(Periodical periodical) {
        periodical.setUpdated_at(LocalDateTime.now());
        periodicalMongoRepository.save(periodical);
        return periodicalSqlRepository.save(periodical);
    }

    public void delete(String id) {
        periodicalMongoRepository.deleteById(id);
        periodicalSqlRepository.deleteById(id);
    }

    public List<Periodical> getAll() {
        return periodicalSqlRepository.findAll();
    }
}
