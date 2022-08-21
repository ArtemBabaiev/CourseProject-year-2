package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:21
 * @class ReaderSqlRepository
 */
public interface ReaderSqlRepository extends JpaRepository<Reader,String> {
}
