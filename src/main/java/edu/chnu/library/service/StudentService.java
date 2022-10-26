package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Student;
import edu.chnu.library.repository.mongo.StudentMongoRepository;
import edu.chnu.library.repository.sql.StudentSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        LocalDateTime time = LocalDateTime.now();
        student.setCreatedAt(time);
        student.setUpdatedAt(time);
        studentMongoRepository.save(student);
        return studentSqlRepository.save(student);
    }

    public Student get(String id) {
        return studentSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Student update(Student student) {
        Student oldOne = get(student.getId());
        student.setCreatedAt(oldOne.getCreatedAt());
        student.setUpdatedAt(LocalDateTime.now());
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

    @Transactional
    public List<Student> getByFaculty(String faculty) {
        return studentSqlRepository.getStudentsByFaculty(faculty);
    }

    @Transactional
    public List<Student> getByUniversity(String university) {
        return studentSqlRepository.getStudentsByUniversity(university);
    }

    public List<Student> getAllByLastNameContaining(String lastName, Sort sort) {
        try {
            return studentSqlRepository.findAllByLastNameContainingIgnoreCase(lastName, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук дорослого з параметрами last_name=" + lastName + "; sort=" + sort.toString());
        }
    }

    public List<Student> getAllByLastNameContainingAndBetween(String lastName, String lastName2, String lastName3, Sort sort) {
        try {
            return studentSqlRepository.findAllByLastNameContainingIgnoreCaseAndLastNameBetween(lastName, lastName2, lastName3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук дорослого з параметрами last_name=" + lastName + "; sort=" + sort.toString() + "; range=[" + lastName2 + ":" + lastName3 + "]");
        }
    }

    public Page<Student> getAllPaginated(PageRequest pageRequest) {
        return studentSqlRepository.findAll(pageRequest);
    }
}
