package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Exemplar;
import edu.chnu.library.model.Literature;
import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.model.Shelf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 31.08.2022 17:23
 * @class ExemplarSqlRepository
 */
public interface ExemplarSqlRepository extends JpaRepository<Exemplar, String> {
    List<Exemplar> findAllByLiterature(Literature literature);

    Page<Exemplar> findAllByLiteratureAndIdContaining(Literature literature, String id, PageRequest pageRequest);

    Page<Exemplar> findAllByLiteratureAndIdContainingAndShelf_BookCase_ReadingRoom(Literature literature, String id, ReadingRoom shelf_bookCase_readingRoom, Pageable pageable);

    List<Exemplar> findAllByShelf(Shelf shelf);
}
