package edu.babaiev.libr.service;

import edu.babaiev.libr.model.WrittenOff;
import edu.babaiev.libr.repository.mongo.WrittenOffMongoRepository;
import edu.babaiev.libr.repository.sql.WrittenOffSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:24
 * @class WrittenOffService
 */
@Service
public class WrittenOffService {
    WrittenOffSqlRepository writtenOffSqlRepository;
    WrittenOffMongoRepository writtenOffMongoRepository;

    @Autowired
    public WrittenOffService(WrittenOffSqlRepository writtenOffSqlRepository, WrittenOffMongoRepository writtenOffMongoRepository) {
        this.writtenOffSqlRepository = writtenOffSqlRepository;
        this.writtenOffMongoRepository = writtenOffMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public WrittenOff create(WrittenOff writtenOff) {
        writtenOff.setCreated_at(LocalDateTime.now());
        writtenOffMongoRepository.save(writtenOff);
        return writtenOffSqlRepository.save(writtenOff);
    }

    public WrittenOff get(String id) {
        return writtenOffSqlRepository.findById(id).orElse(null);
    }

    public WrittenOff update(WrittenOff writtenOff) {
        writtenOff.setUpdate_at(LocalDateTime.now());
        writtenOffMongoRepository.save(writtenOff);
        return writtenOffSqlRepository.save(writtenOff);
    }

    public void delete(String id) {
        writtenOffMongoRepository.deleteById(id);
        writtenOffSqlRepository.deleteById(id);
    }

    public List<WrittenOff> getAll() {
        return writtenOffSqlRepository.findAll();
    }
}
