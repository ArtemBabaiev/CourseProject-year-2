package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Student;
import edu.babaiev.libr.repository.mongo.StudentMongoRepository;
import edu.babaiev.libr.repository.sql.StudentSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:21
 * @class StudentService
 */
@Service
public class StudentService {
    StudentSqlRepository studentSqlRepository;
    StudentMongoRepository studentMongoRepository;

    @Autowired
    public StudentService(StudentSqlRepository studentSqlRepository, StudentMongoRepository studentMongoRepository) {
        this.studentSqlRepository = studentSqlRepository;
        this.studentMongoRepository = studentMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Student create(Student student) {
        student.setCreated_at(LocalDateTime.now());
        studentMongoRepository.save(student);
        return studentSqlRepository.save(student);
    }

    public Student get(String id) {
        return studentSqlRepository.findById(id).orElse(null);
    }

    public Student update(Student student) {
        student.setUpdate_at(LocalDateTime.now());
        studentMongoRepository.save(student);
        return studentSqlRepository.save(student);
    }

    public void delete(String id) {
        studentMongoRepository.deleteById(id);
        studentSqlRepository.deleteById(id);
    }

    public List<Student> getAll() {
        return studentSqlRepository.findAll();
    }
}
