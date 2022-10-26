package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Author;
import edu.chnu.library.model.Author;
import edu.chnu.library.repository.mongo.AuthorMongoRepository;
import edu.chnu.library.repository.sql.AuthorSqlRepository;
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
        author.setCreatedAt(time);
        author.setUpdatedAt(time);
        authorMongoRepository.save(author);
        return authorSqlRepository.save(author);
    }

    public Author get(String id) {
        return authorSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Author update(Author author) {
        Author oldOne = get(author.getId());
        author.setCreatedAt(oldOne.getCreatedAt());
        author.setUpdatedAt(LocalDateTime.now());
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

    public Page<Author> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return authorSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }
    public List<Author> getAllByNameContaining(String name, Sort sort) {
        try {
            return authorSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<Author> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return authorSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<Author> getAllPaginated(PageRequest pageRequest){
        return authorSqlRepository.findAll(pageRequest);
    }
}
