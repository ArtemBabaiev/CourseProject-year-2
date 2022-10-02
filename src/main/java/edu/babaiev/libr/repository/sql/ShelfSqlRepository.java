package edu.babaiev.libr.repository.sql;

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
 * @date 16.08.2022 20:48
 * @class ShelfSqlRepository
 */
public interface ShelfSqlRepository extends JpaRepository<Shelf, String> {
    Page<Shelf> findAllByNumberContainingIgnoreCase(String number, Pageable pageable);
    List<Shelf> findAllByBookCase(BookCase bookCase);

}
