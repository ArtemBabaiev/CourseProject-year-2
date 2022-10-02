package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Writing;
import edu.babaiev.libr.repository.mongo.WritingMongoRepository;
import edu.babaiev.libr.repository.sql.WritingSqlRepository;
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
 * @date 16.08.2022 23:23
 * @class WritingService
 */
@Service
public class WritingService {
    WritingSqlRepository writingSqlRepository;
    WritingMongoRepository writingMongoRepository;

    @Autowired
    public WritingService(WritingSqlRepository writingSqlRepository, WritingMongoRepository writingMongoRepository) {
        this.writingSqlRepository = writingSqlRepository;
        this.writingMongoRepository = writingMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Writing create(Writing writing) {
        LocalDateTime time = LocalDateTime.now();
        writing.setCreatedAt(time);
        writing.setUpdatedAt(time);
        writingMongoRepository.save(writing);
        return writingSqlRepository.save(writing);
    }

    public Writing get(String id) {
        return writingSqlRepository.findById(id).orElse(null);
    }

    public Writing update(Writing writing) {
        Writing oldOne = get(writing.getId());
        writing.setCreatedAt(oldOne.getCreatedAt());
        writing.setUpdatedAt(LocalDateTime.now());
        writingMongoRepository.save(writing);
        return writingSqlRepository.save(writing);
    }

    public void delete(String id) {
        writingMongoRepository.deleteById(id);
        writingSqlRepository.deleteById(id);
    }

    public List<Writing> getAll() {
        return writingSqlRepository.findAll();
    }
    public Page<Writing> getByNameContainingPaginated(String name, PageRequest pageRequest){
        return writingSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }
}
