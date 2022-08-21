package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Genre;
import edu.babaiev.libr.repository.mongo.GenreMongoRepository;
import edu.babaiev.libr.repository.sql.GenreSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 22:57
 * @class GenreService
 */
@Service
public class GenreService {
    GenreSqlRepository genreSqlRepository;
    GenreMongoRepository genreMongoRepository;

    @Autowired
    public GenreService(GenreSqlRepository genreSqlRepository, GenreMongoRepository genreMongoRepository) {
        this.genreSqlRepository = genreSqlRepository;
        this.genreMongoRepository = genreMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Genre create(Genre genre) {
        genre.setCreated_at(LocalDateTime.now());
        genreMongoRepository.save(genre);
        return genreSqlRepository.save(genre);
    }

    public Genre get(String id) {
        return genreSqlRepository.findById(id).orElse(null);
    }

    public Genre update(Genre genre) {
        genre.setUpdated_at(LocalDateTime.now());
        genreMongoRepository.save(genre);
        return genreSqlRepository.save(genre);
    }

    public void delete(String id) {
        genreMongoRepository.deleteById(id);
        genreSqlRepository.deleteById(id);
    }

    public List<Genre> getAll() {
        return genreSqlRepository.findAll();
    }
}
