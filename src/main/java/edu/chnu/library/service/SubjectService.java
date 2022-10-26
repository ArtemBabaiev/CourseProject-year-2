package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Subject;
import edu.chnu.library.repository.mongo.SubjectMongoRepository;
import edu.chnu.library.repository.sql.SubjectSqlRepository;
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
 * @date 16.08.2022 23:22
 * @class SubjectService
 */
@Service
public class SubjectService {
    SubjectSqlRepository subjectSqlRepository;
    SubjectMongoRepository subjectMongoRepository;

    @Autowired
    public SubjectService(SubjectSqlRepository subjectSqlRepository, SubjectMongoRepository subjectMongoRepository) {
        this.subjectSqlRepository = subjectSqlRepository;
        this.subjectMongoRepository = subjectMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Subject create(Subject subject) {
        LocalDateTime time = LocalDateTime.now();
        subject.setCreatedAt(time);
        subject.setUpdatedAt(time);
        subjectMongoRepository.save(subject);
        return subjectSqlRepository.save(subject);
    }

    public Subject get(String id) {
        return subjectSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Subject update(Subject subject) {
        Subject oldOne = get(subject.getId());
        subject.setCreatedAt(oldOne.getCreatedAt());
        subject.setUpdatedAt(LocalDateTime.now());
        subjectMongoRepository.save(subject);
        return subjectSqlRepository.save(subject);
    }

    public void delete(String id) {
        subjectMongoRepository.deleteById(id);
        subjectSqlRepository.deleteById(id);
    }

    public List<Subject> getAll() {
        return subjectSqlRepository.findAll();
    }

    public Page<Subject> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return subjectSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public List<Subject> getAllByNameContaining(String name, Sort sort) {
        try {
            return subjectSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<Subject> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return subjectSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<Subject> getAllPaginated(PageRequest pageRequest) {
        return subjectSqlRepository.findAll(pageRequest);
    }
}
