package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Pupil;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:20
 * @class PupilSqlRepository
 */
public interface PupilSqlRepository extends JpaRepository<Pupil, String> {
    List<Pupil> findAllByLastNameContainingIgnoreCaseAndLastNameBetween(String lastName, String lastName2, String lastName3, Sort sort);

    List<Pupil> findAllByLastNameContainingIgnoreCase(String lastName, Sort sort);
}
