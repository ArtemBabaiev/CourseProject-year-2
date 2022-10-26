package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:50
 * @class GenreSqlRepository
 */
public interface GenreSqlRepository extends JpaRepository<Genre, String> {
    Page<Genre> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Genre> findAllByNameContainingIgnoreCase(String name, Sort sort);

    List<Genre> findAllByNameContainingIgnoreCaseAndNameBetween(String name, String name2, String name3, Sort sort);
}
