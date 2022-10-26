package edu.chnu.library.repository.sql;

import edu.chnu.library.model.BookCase;
import edu.chnu.library.model.Library;
import edu.chnu.library.model.ReadingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:33
 * @class BookCaseSqlRepository
 */
public interface BookCaseSqlRepository extends JpaRepository<BookCase, String> {
    Page<BookCase> findAllByNumberContainingIgnoreCase(String number, Pageable pageable);
    List<BookCase> findAllByNumberContainingIgnoreCase(String number, Sort sort);

    List<BookCase> findAllByReadingRoom(ReadingRoom readingRoom);

    List<BookCase> findAllByNumberContainingIgnoreCaseAndNumberBetween(String number, String number2, String number3, Sort sort);
}
