package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Shelf;
import edu.babaiev.libr.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:58
 * @class SubjectSqlRepository
 */
public interface SubjectSqlRepository extends JpaRepository<Subject, String> {
    Page<Subject> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
