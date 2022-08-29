package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Key;
import edu.babaiev.libr.repository.mongo.KeyMongoRepository;
import edu.babaiev.libr.repository.sql.KeySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:00
 * @class KeyService
 */
@Service
public class KeyService {
    KeySqlRepository keySqlRepository;
    KeyMongoRepository keyMongoRepository;

    @Autowired
    public KeyService(KeySqlRepository keySqlRepository, KeyMongoRepository keyMongoRepository) {
        this.keySqlRepository = keySqlRepository;
        this.keyMongoRepository = keyMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Key create(Key key) {
        LocalDateTime time = LocalDateTime.now();
        key.setCreated_at(time);
        key.setUpdated_at(time);
        keyMongoRepository.save(key);
        return keySqlRepository.save(key);
    }

    public Key get(String id) {
        return keySqlRepository.findById(id).orElse(null);
    }

    public Key update(Key key) {
        key.setUpdated_at(LocalDateTime.now());
        keyMongoRepository.save(key);
        return keySqlRepository.save(key);
    }

    public void delete(String id) {
        keyMongoRepository.deleteById(id);
        keySqlRepository.deleteById(id);
    }

    public List<Key> getAll() {
        return keySqlRepository.findAll();
    }
}
