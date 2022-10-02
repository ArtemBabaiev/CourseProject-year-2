package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.BookType;
import edu.babaiev.libr.model.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:05
 * @class CollectionSqlRepository
 */
public interface CollectionSqlRepository extends JpaRepository<Collection, String> {
    Page<Collection> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
