package edu.chnu.library.service;

import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Key;
import edu.chnu.library.repository.mongo.KeyMongoRepository;
import edu.chnu.library.repository.sql.KeySqlRepository;
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
        key.setCreatedAt(time);
        key.setUpdatedAt(time);
        keyMongoRepository.save(key);
        return keySqlRepository.save(key);
    }

    public Key get(String id) {
        return keySqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Key update(Key key) {
        Key oldOne = get(key.getId());
        key.setCreatedAt(oldOne.getCreatedAt());
        key.setUpdatedAt(LocalDateTime.now());
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

    public Key getByLogin(String login) {
        return keySqlRepository.findKeyByLogin(login);
    }
}
