package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Record;
import edu.babaiev.libr.repository.mongo.RecordMongoRepository;
import edu.babaiev.libr.repository.sql.RecordSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        record.setCreatedAt(time);
        record.setUpdatedAt(time);
        recordMongoRepository.save(record);
        return recordSqlRepository.save(record);
    }

    public Record get(String id) {
        return recordSqlRepository.findById(id).orElse(null);
    }

    public Record update(Record record) {
        Record oldOne = get(record.getId());
        record.setCreatedAt(oldOne.getCreatedAt());
        record.setUpdatedAt(LocalDateTime.now());
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

    public Page<Record> getAllByReaderLastNameContainingPaginated(String lastName, PageRequest pageRequest){
        return recordSqlRepository.findAllByReader_LastNameContainingIgnoreCase(lastName, pageRequest);
    }
}
