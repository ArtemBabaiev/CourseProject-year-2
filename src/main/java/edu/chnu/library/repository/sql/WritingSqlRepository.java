package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Writing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:33
 * @class WritingSqlRepository
 */
public interface WritingSqlRepository extends JpaRepository<Writing, String> {
    Page<Writing> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Writing> findAllByNameContainingIgnoreCase(String name, Sort sort);

    @Procedure("getMostPopularWritings")
    List<String> getMostPopularWritings();

    List<Writing> findAllByNameContainingIgnoreCaseAndNameBetween(String name, String name2, String name3, Sort sort);
}
