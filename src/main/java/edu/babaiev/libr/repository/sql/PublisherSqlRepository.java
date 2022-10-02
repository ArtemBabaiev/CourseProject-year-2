package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.PeriodicalType;
import edu.babaiev.libr.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:30
 * @class PublisherSqlRepository
 */
public interface PublisherSqlRepository extends JpaRepository<Publisher, String> {
    Page<Publisher> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
