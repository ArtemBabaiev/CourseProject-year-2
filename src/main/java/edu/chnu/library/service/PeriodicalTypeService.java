package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.PeriodicalType;
import edu.chnu.library.repository.mongo.PeriodicalTypeMongoRepository;
import edu.chnu.library.repository.sql.PeriodicalTypeSqlRepository;
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
        LocalDateTime time = LocalDateTime.now();
        periodicalType.setCreatedAt(time);
        periodicalType.setUpdatedAt(time);
        periodicalTypeMongoRepository.save(periodicalType);
        return periodicalTypeSqlRepository.save(periodicalType);
    }

    public PeriodicalType get(String id) {
        return periodicalTypeSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public PeriodicalType update(PeriodicalType periodicalType) {
        PeriodicalType oldOne = get(periodicalType.getId());
        periodicalType.setCreatedAt(oldOne.getCreatedAt());
        periodicalType.setUpdatedAt(LocalDateTime.now());
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

    public Page<PeriodicalType> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return periodicalTypeSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public List<PeriodicalType> getAllByNameContaining(String name, Sort sort) {
        try {
            return periodicalTypeSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<PeriodicalType> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return periodicalTypeSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<PeriodicalType> getAllPaginated(PageRequest pageRequest) {
        return periodicalTypeSqlRepository.findAll(pageRequest);
    }
}
