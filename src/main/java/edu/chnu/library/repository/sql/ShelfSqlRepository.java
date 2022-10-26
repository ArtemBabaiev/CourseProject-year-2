package edu.chnu.library.repository.sql;

import edu.chnu.library.model.BookCase;
import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.model.Shelf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:48
 * @class ShelfSqlRepository
 */
public interface ShelfSqlRepository extends JpaRepository<Shelf, String> {
    Page<Shelf> findAllByNumberContainingIgnoreCase(String number, Pageable pageable);

    List<Shelf> findAllByNumberContainingIgnoreCase(String number, Sort sort);

    List<Shelf> findAllByBookCase(BookCase bookCase);

    List<Shelf> findAllByBookCase_ReadingRoom(ReadingRoom bookCase_readingRoom);

    List<Shelf> findAllByNumberContainingIgnoreCaseAndNumberBetween(String number, String number2, String number3, Sort sort);
}
