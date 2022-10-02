package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Author;
import edu.babaiev.libr.model.BookCase;
import edu.babaiev.libr.model.ReadingRoom;
import edu.babaiev.libr.model.Shelf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    List<BookCase> findAllByReadingRoom(ReadingRoom readingRoom);

}
