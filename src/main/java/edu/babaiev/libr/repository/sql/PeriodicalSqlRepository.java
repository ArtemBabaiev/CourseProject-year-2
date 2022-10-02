package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.MonographType;
import edu.babaiev.libr.model.Periodical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:10
 * @class PeriodicalSqlRepository
 */
public interface PeriodicalSqlRepository extends JpaRepository<Periodical, String> {
    Page<Periodical> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
