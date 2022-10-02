package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Library;
import edu.babaiev.libr.model.Monograph;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:06
 * @class MonographSqlRepository
 */
public interface MonographSqlRepository extends JpaRepository<Monograph, String> {
    Page<Monograph> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
