package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Library;
import edu.babaiev.libr.repository.mongo.LibraryMongoRepository;
import edu.babaiev.libr.repository.sql.LibrarySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:01
 * @class LibraryService
 */
@Service
public class LibraryService {
    LibrarySqlRepository librarySqlRepository;
    LibraryMongoRepository libraryMongoRepository;

    @Autowired
    public LibraryService(LibrarySqlRepository librarySqlRepository, LibraryMongoRepository libraryMongoRepository) {
        this.librarySqlRepository = librarySqlRepository;
        this.libraryMongoRepository = libraryMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Library create(Library library) {
        library.setCreated_at(LocalDateTime.now());
        libraryMongoRepository.save(library);
        return librarySqlRepository.save(library);
    }

    public Library get(String id) {
        return librarySqlRepository.findById(id).orElse(null);
    }

    public Library update(Library library) {
        library.setUpdate_at(LocalDateTime.now());
        libraryMongoRepository.save(library);
        return librarySqlRepository.save(library);
    }

    public void delete(String id) {
        libraryMongoRepository.deleteById(id);
        librarySqlRepository.deleteById(id);
    }

    public List<Library> getAll() {
        return librarySqlRepository.findAll();
    }
}
