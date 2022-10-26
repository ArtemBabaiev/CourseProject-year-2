package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:05
 * @class CollectionSqlRepository
 */
public interface CollectionSqlRepository extends JpaRepository<Collection, String> {
    Page<Collection> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Collection> findAllByNameContainingIgnoreCase(String name, Sort sort);

    List<Collection> findAllByNameContainingIgnoreCaseAndNameBetween(String name, String name2, String name3, Sort sort);
}
