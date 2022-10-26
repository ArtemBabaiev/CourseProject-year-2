package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Adult;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:14
 * @class AdultSqlRepository
 */
public interface AdultSqlRepository extends JpaRepository<Adult, String> {
    List<Adult> findAllByLastNameContainingIgnoreCase(String lastName, Sort sort);
    List<Adult> findAllByLastNameContainingIgnoreCaseAndLastNameBetween(String lastName, String lastName2, String lastName3, Sort sort);
}
