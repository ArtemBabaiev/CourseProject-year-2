package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:23
 * @class ArticleSqlRepository
 */
public interface ArticleSqlRepository extends JpaRepository<Article, String> {
    Page<Article> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
