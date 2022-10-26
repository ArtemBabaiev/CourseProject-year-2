package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Genre;
import edu.chnu.library.repository.mongo.GenreMongoRepository;
import edu.chnu.library.repository.sql.GenreSqlRepository;
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
        LocalDateTime time = LocalDateTime.now();
        genre.setCreatedAt(time);
        genre.setUpdatedAt(time);
        genreMongoRepository.save(genre);
        return genreSqlRepository.save(genre);
    }

    public Genre get(String id) {
        return genreSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Genre update(Genre genre) {
        Genre oldOne = get(genre.getId());
        genre.setCreatedAt(oldOne.getCreatedAt());
        genre.setUpdatedAt(LocalDateTime.now());
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

    public Page<Genre> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return genreSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    public List<Genre> getAllByNameContaining(String name, Sort sort) {
        try {
            return genreSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<Genre> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return genreSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<Genre> getAllPaginated(PageRequest pageRequest) {
        return genreSqlRepository.findAll(pageRequest);
    }
}
