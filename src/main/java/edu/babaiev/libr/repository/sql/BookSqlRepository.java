package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 23:18
 * @class BookSqlRepository
 */
public interface BookSqlRepository extends JpaRepository<Book, String> {
}
