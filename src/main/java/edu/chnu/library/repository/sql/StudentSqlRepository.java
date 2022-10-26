package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:23
 * @class StudentSqlRepository
 */
public interface StudentSqlRepository extends JpaRepository<Student, String> {

    @Procedure("getStudentsByFaculty")
    List<Student> getStudentsByFaculty(String facultyName);

    @Procedure("getStudentsByUniversity")
    List<Student> getStudentsByUniversity(String universityName);

    List<Student> findAllByLastNameContainingIgnoreCase(String lastName, Sort sort);

    List<Student> findAllByLastNameContainingIgnoreCaseAndLastNameBetween(String lastName, String lastName2, String lastName3, Sort sort);
}
