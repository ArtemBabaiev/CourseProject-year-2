package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Subject;
import edu.babaiev.libr.repository.mongo.SubjectMongoRepository;
import edu.babaiev.libr.repository.sql.SubjectSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        subject.setCreated_at(time);
        subject.setUpdated_at(time);
        subjectMongoRepository.save(subject);
        return subjectSqlRepository.save(subject);
    }

    public Subject get(String id) {
        return subjectSqlRepository.findById(id).orElse(null);
    }

    public Subject update(Subject subject) {
        Subject oldOne = get(subject.getId());
        subject.setCreated_at(oldOne.getCreated_at());
        subject.setUpdated_at(LocalDateTime.now());
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
}
