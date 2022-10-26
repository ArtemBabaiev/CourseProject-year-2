package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Scientist;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 19.08.2022 22:50
 * @class ScientistSqlRepository
 */
public interface ScientistSqlRepository extends JpaRepository<Scientist, String> {
    List<Scientist> findAllByLastNameContainingIgnoreCase(String lastName, Sort sort);

    List<Scientist> findAllByLastNameContainingIgnoreCaseAndLastNameBetween(String lastName, String lastName2, String lastName3, Sort sort);
}
