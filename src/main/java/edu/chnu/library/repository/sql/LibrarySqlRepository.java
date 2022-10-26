package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:34
 * @class LibrarySqlRepository
 */
public interface LibrarySqlRepository extends JpaRepository<Library, String> {
    Page<Library> findAllByNumberContainingIgnoreCase(String number, Pageable pageable);

    List<Library> findAllByNumberContainingIgnoreCase(String number, Sort sort);

    List<Library> findAllByNumberContainingIgnoreCaseAndNumberBetween(String number, String number2, String number3, Sort sort);
}
