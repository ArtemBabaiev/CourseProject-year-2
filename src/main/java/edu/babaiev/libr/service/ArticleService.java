package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Article;
import edu.babaiev.libr.repository.mongo.ArticleMongoRepository;
import edu.babaiev.libr.repository.sql.ArticleSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 21:34
 * @class ArticleService
 */
@Service
public class ArticleService {
    ArticleSqlRepository articleSqlRepository;
    ArticleMongoRepository articleMongoRepository;

    @Autowired
    public ArticleService(ArticleSqlRepository articleSqlRepository, ArticleMongoRepository articleMongoRepository) {
        this.articleSqlRepository = articleSqlRepository;
        this.articleMongoRepository = articleMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Article create(Article article) {
        LocalDateTime time = LocalDateTime.now();
        article.setCreatedAt(time);
        article.setUpdatedAt(time);
        articleMongoRepository.save(article);
        return articleSqlRepository.save(article);
    }

    public Article get(String id) {
        return articleSqlRepository.findById(id).orElse(null);
    }

    public Article update(Article article) {
        Article oldOne = get(article.getId());
        article.setCreatedAt(oldOne.getCreatedAt());
        article.setUpdatedAt(LocalDateTime.now());
        articleMongoRepository.save(article);
        return articleSqlRepository.save(article);
    }

    public void delete(String id) {
        articleMongoRepository.deleteById(id);
        articleSqlRepository.deleteById(id);
    }

    public List<Article> getAll() {
        return articleSqlRepository.findAll();
    }
    public Page<Article> getByNameContainingPaginated(String name, PageRequest pageRequest){
        return articleSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }
}
