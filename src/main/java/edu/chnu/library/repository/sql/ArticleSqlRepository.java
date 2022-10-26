package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:23
 * @class ArticleSqlRepository
 */
public interface ArticleSqlRepository extends JpaRepository<Article, String> {
    Page<Article> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Article> findAllByNameContainingIgnoreCase(String name, Sort sort);

    List<Article> findAllByNameContainingIgnoreCaseAndNameBetween(String name, String name2, String name3, Sort sort);
}
