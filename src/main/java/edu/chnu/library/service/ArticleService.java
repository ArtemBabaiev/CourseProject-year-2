package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Article;
import edu.chnu.library.repository.mongo.ArticleMongoRepository;
import edu.chnu.library.repository.sql.ArticleSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        return articleSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Статтю з id=" + id + " не знайдено"));
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

    public Page<Article> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return articleSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public List<Article> getAllByNameContaining(String name, Sort sort) {
        try {
            return articleSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<Article> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return articleSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<Article> getAllPaginated(PageRequest pageRequest) {
        return articleSqlRepository.findAll(pageRequest);
    }
}
