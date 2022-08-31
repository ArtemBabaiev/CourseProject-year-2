package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Writing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:33
 * @class WritingSqlRepository
 */
public interface WritingSqlRepository extends JpaRepository<Writing, String> {
}
