package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Scientist;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 19.08.2022 22:50
 * @class ScientistSqlRepository
 */
public interface ScientistSqlRepository extends JpaRepository<Scientist, String> {
}
