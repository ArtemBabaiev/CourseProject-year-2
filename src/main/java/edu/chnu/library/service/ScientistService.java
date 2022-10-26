package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Scientist;
import edu.chnu.library.repository.mongo.ScientistMongoRepository;
import edu.chnu.library.repository.sql.ScientistSqlRepository;
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
        LocalDateTime time = LocalDateTime.now();
        scientist.setCreatedAt(time);
        scientist.setUpdatedAt(time);
        scientistMongoRepository.save(scientist);
        return scientistSqlRepository.save(scientist);
    }

    public Scientist get(String id) {
        return scientistSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Scientist update(Scientist scientist) {
        Scientist oldOne = get(scientist.getId());
        scientist.setCreatedAt(oldOne.getCreatedAt());
        scientist.setUpdatedAt(LocalDateTime.now());
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

    public List<Scientist> getAllByLastNameContaining(String lastName, Sort sort) {
        try {
            return scientistSqlRepository.findAllByLastNameContainingIgnoreCase(lastName, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук дорослого з параметрами last_name=" + lastName + "; sort=" + sort.toString());
        }
    }

    public List<Scientist> getAllByLastNameContainingAndBetween(String lastName, String lastName2, String lastName3, Sort sort) {
        try {
            return scientistSqlRepository.findAllByLastNameContainingIgnoreCaseAndLastNameBetween(lastName, lastName2, lastName3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук дорослого з параметрами last_name=" + lastName + "; sort=" + sort.toString() + "; range=[" + lastName2 + ":" + lastName3 + "]");
        }
    }

    public Page<Scientist> getAllPaginated(PageRequest pageRequest) {
        return scientistSqlRepository.findAll(pageRequest);
    }
}
