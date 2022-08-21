package edu.babaiev.libr.repository.mongo;

import edu.babaiev.libr.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:23
 * @class ArticleMongoRepository
 */
public interface ArticleMongoRepository extends MongoRepository<Article, String> {
}
