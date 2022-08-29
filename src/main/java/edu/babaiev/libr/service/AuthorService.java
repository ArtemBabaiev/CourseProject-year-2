package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Author;
import edu.babaiev.libr.repository.mongo.AuthorMongoRepository;
import edu.babaiev.libr.repository.sql.AuthorSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 21:53
 * @class AuthorService
 */
@Service
public class AuthorService {
    AuthorSqlRepository authorSqlRepository;
    AuthorMongoRepository authorMongoRepository;

    @Autowired
    public AuthorService(AuthorSqlRepository authorSqlRepository, AuthorMongoRepository authorMongoRepository) {
        this.authorSqlRepository = authorSqlRepository;
        this.authorMongoRepository = authorMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Author create(Author author) {
        LocalDateTime time = LocalDateTime.now();
        author.setCreated_at(time);
        author.setUpdated_at(time);
        authorMongoRepository.save(author);
        return authorSqlRepository.save(author);
    }

    public Author get(String id) {
        return authorSqlRepository.findById(id).orElse(null);
    }

    public Author update(Author author) {
        author.setUpdated_at(LocalDateTime.now());
        authorMongoRepository.save(author);
        return authorSqlRepository.save(author);
    }

    public void delete(String id) {
        authorMongoRepository.deleteById(id);
        authorSqlRepository.deleteById(id);
    }

    public List<Author> getAll() {
        return authorSqlRepository.findAll();
    }
}
