package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Reader;
import edu.babaiev.libr.repository.mongo.ReaderMongoRepository;
import edu.babaiev.libr.repository.sql.ReaderSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:15
 * @class ReaderService
 */
@Service
public class ReaderService {
    ReaderSqlRepository readerSqlRepository;
    ReaderMongoRepository readerMongoRepository;

    @Autowired
    public ReaderService(ReaderSqlRepository readerSqlRepository, ReaderMongoRepository readerMongoRepository) {
        this.readerSqlRepository = readerSqlRepository;
        this.readerMongoRepository = readerMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Reader create(Reader reader) {
        reader.setCreated_at(LocalDateTime.now());
        readerMongoRepository.save(reader);
        return readerSqlRepository.save(reader);
    }

    public Reader get(String id) {
        return readerSqlRepository.findById(id).orElse(null);
    }

    public Reader update(Reader reader) {
        reader.setUpdated_at(LocalDateTime.now());
        readerMongoRepository.save(reader);
        return readerSqlRepository.save(reader);
    }

    public void delete(String id) {
        readerMongoRepository.deleteById(id);
        readerSqlRepository.deleteById(id);
    }

    public List<Reader> getAll() {
        return readerSqlRepository.findAll();
    }
}
