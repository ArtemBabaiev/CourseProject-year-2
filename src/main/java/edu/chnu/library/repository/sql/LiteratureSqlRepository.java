package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Literature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 23:48
 * @class LiteratureSqlRepository
 */
public interface LiteratureSqlRepository extends JpaRepository<Literature, String> {
    Page<Literature> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    @Procedure("getLiteratureCreatedInPeriod")
    List<Literature> getLiteratureCreatedInPeriod(String lowerDate,String upperDate);

    @Procedure("getLiteratureUsedByReaderInPeriod")
    List<Literature> getLiteratureUsedByReaderInPeriod(String readerId, String lowerDate, String upperDate);

    @Procedure("getLitrContainsAuthor")
    List<Literature> getLitrContainsAuthor(String authorId);

    @Procedure("getLitrContainsWriting")
    List<Literature> getLitrContainsWriting(String writingId);
}
