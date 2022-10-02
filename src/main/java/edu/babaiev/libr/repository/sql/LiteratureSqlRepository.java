package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Literature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 23:48
 * @class LiteratureSqlRepository
 */
public interface LiteratureSqlRepository extends JpaRepository<Literature, String> {
    Page<Literature> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
