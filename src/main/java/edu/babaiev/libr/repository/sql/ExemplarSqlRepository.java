package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 31.08.2022 17:23
 * @class ExemplarSqlRepository
 */
public interface ExemplarSqlRepository extends JpaRepository<Exemplar, String> {
}
