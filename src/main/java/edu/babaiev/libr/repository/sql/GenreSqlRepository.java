package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Employee;
import edu.babaiev.libr.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:50
 * @class GenreSqlRepository
 */
public interface GenreSqlRepository extends JpaRepository<Genre, String> {
    Page<Genre> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
