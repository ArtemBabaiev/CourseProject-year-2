package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 23:18
 * @class BookSqlRepository
 */
public interface BookSqlRepository extends JpaRepository<Book, String> {
    Page<Book> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Book> findAllByNameContainingIgnoreCase(String name, Sort sort);

    List<Book> findAllByNameContainingIgnoreCaseAndNameBetween(String name, String name2, String name3, Sort sort);
}
