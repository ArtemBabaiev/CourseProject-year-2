package edu.babaiev.libr.service;

import edu.babaiev.libr.model.PeriodicalType;
import edu.babaiev.libr.repository.mongo.PeriodicalTypeMongoRepository;
import edu.babaiev.libr.repository.sql.PeriodicalTypeSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:10
 * @class PeriodicalTypeService
 */
@Service
public class PeriodicalTypeService {
    PeriodicalTypeSqlRepository periodicalTypeSqlRepository;
    PeriodicalTypeMongoRepository periodicalTypeMongoRepository;

    @Autowired
    public PeriodicalTypeService(PeriodicalTypeSqlRepository periodicalTypeSqlRepository, PeriodicalTypeMongoRepository periodicalTypeMongoRepository) {
        this.periodicalTypeSqlRepository = periodicalTypeSqlRepository;
        this.periodicalTypeMongoRepository = periodicalTypeMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public PeriodicalType create(PeriodicalType periodicalType) {
        periodicalType.setCreated_at(LocalDateTime.now());
        periodicalTypeMongoRepository.save(periodicalType);
        return periodicalTypeSqlRepository.save(periodicalType);
    }

    public PeriodicalType get(String id) {
        return periodicalTypeSqlRepository.findById(id).orElse(null);
    }

    public PeriodicalType update(PeriodicalType periodicalType) {
        periodicalType.setUpdated_at(LocalDateTime.now());
        periodicalTypeMongoRepository.save(periodicalType);
        return periodicalTypeSqlRepository.save(periodicalType);
    }

    public void delete(String id) {
        periodicalTypeMongoRepository.deleteById(id);
        periodicalTypeSqlRepository.deleteById(id);
    }

    public List<PeriodicalType> getAll() {
        return periodicalTypeSqlRepository.findAll();
    }
}
