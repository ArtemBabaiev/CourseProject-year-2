package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:27
 * @class AuthorSqlRepository
 */
public interface AuthorSqlRepository extends JpaRepository<Author, String> {
    Page<Author> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
