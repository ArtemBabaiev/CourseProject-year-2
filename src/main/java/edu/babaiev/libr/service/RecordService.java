package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Record;
import edu.babaiev.libr.repository.mongo.RecordMongoRepository;
import edu.babaiev.libr.repository.sql.RecordSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:17
 * @class RecordService
 */
@Service
public class RecordService {
    RecordSqlRepository recordSqlRepository;
    RecordMongoRepository recordMongoRepository;

    @Autowired
    public RecordService(RecordSqlRepository recordSqlRepository, RecordMongoRepository recordMongoRepository) {
        this.recordSqlRepository = recordSqlRepository;
        this.recordMongoRepository = recordMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Record create(Record record) {
        LocalDateTime time = LocalDateTime.now();
        record.setCreated_at(time);
        record.setUpdated_at(time);
        recordMongoRepository.save(record);
        return recordSqlRepository.save(record);
    }

    public Record get(String id) {
        return recordSqlRepository.findById(id).orElse(null);
    }

    public Record update(Record record) {
        Record oldOne = get(record.getId());
        record.setCreated_at(oldOne.getCreated_at());
        record.setUpdated_at(LocalDateTime.now());
        recordMongoRepository.save(record);
        return recordSqlRepository.save(record);
    }

    public void delete(String id) {
        recordMongoRepository.deleteById(id);
        recordSqlRepository.deleteById(id);
    }

    public List<Record> getAll() {
        return recordSqlRepository.findAll();
    }
}
