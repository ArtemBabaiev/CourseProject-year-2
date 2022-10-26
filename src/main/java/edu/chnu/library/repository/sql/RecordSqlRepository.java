package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:31
 * @class RecordSqlRepository
 */
public interface RecordSqlRepository extends JpaRepository<Record, String> {
    Page<Record> findAllByReader_LastNameContainingIgnoreCase(String lastName, Pageable pageable);

    List<Record> findAllByReader_LastNameContainingIgnoreCase(String lastName, Sort sort);

    @Procedure("getReadersCountForAllEmployees")
    Object getReadersCountForAllEmployees();

    List<Record> findAllByReader_LastNameContainingIgnoreCaseAndReader_LastNameBetween(String lastName, String lastName2, String lastName3, Sort sort);
}
