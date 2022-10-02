package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Exemplar;
import edu.babaiev.libr.model.Literature;
import edu.babaiev.libr.model.Shelf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
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

    List<Exemplar> findAllByShelf(Shelf shelf);
}
