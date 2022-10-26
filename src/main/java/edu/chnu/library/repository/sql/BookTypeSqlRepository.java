package edu.chnu.library.repository.sql;

import edu.chnu.library.model.BookType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:49
 * @class BookTypeSqlRepository
 */
public interface BookTypeSqlRepository extends JpaRepository<BookType, String> {
    Page<BookType> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    List<BookType> findAllByNameContainingIgnoreCase(String name, Sort sort);

    List<BookType> findAllByNameContainingIgnoreCaseAndNameBetween(String name, String name2, String name3, Sort sort);
}
