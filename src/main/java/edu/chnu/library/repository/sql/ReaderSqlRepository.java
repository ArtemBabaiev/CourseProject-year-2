package edu.chnu.library.repository.sql;

import edu.chnu.library.dto.ReaderActivityDTO;
import edu.chnu.library.model.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:21
 * @class ReaderSqlRepository
 */
public interface ReaderSqlRepository extends JpaRepository<Reader, String> {
    Page<Reader> findAllByLastNameContaining(String lastName, PageRequest pageRequest);

    @Procedure("getReadersByLendedLiterature")
    List<Reader> getReadersByLendedLiterature(String litId);

    @Procedure("getReadersWithNoActivityInPeriod")
    List<ReaderActivityDTO> getReadersWithNoActivityInPeriod(String lowerDate, String upperDate);

    @Procedure("getReadersWithOverdue")
    List<Reader> getReadersWithOverdue();
}
