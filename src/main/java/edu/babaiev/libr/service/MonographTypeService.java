package edu.babaiev.libr.service;

import edu.babaiev.libr.model.MonographType;
import edu.babaiev.libr.repository.mongo.MonographTypeMongoRepository;
import edu.babaiev.libr.repository.sql.MonographTypeSqlRepository;
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
 * @date 16.08.2022 23:07
 * @class MonographTypeService
 */
@Service
public class MonographTypeService {
    MonographTypeSqlRepository monographTypeSqlRepository;
    MonographTypeMongoRepository monographTypeMongoRepository;

    @Autowired
    public MonographTypeService(MonographTypeSqlRepository monographTypeSqlRepository, MonographTypeMongoRepository monographTypeMongoRepository) {
        this.monographTypeSqlRepository = monographTypeSqlRepository;
        this.monographTypeMongoRepository = monographTypeMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public MonographType create(MonographType monographType) {
        LocalDateTime time = LocalDateTime.now();
        monographType.setCreatedAt(time);
        monographType.setUpdatedAt(time);
        monographTypeMongoRepository.save(monographType);
        return monographTypeSqlRepository.save(monographType);
    }

    public MonographType get(String id) {
        return monographTypeSqlRepository.findById(id).orElse(null);
    }

    public MonographType update(MonographType monographType) {
        MonographType oldOne = get(monographType.getId());
        monographType.setCreatedAt(oldOne.getCreatedAt());
        monographType.setUpdatedAt(LocalDateTime.now());
        monographTypeMongoRepository.save(monographType);
        return monographTypeSqlRepository.save(monographType);
    }

    public void delete(String id) {
        monographTypeMongoRepository.deleteById(id);
        monographTypeSqlRepository.deleteById(id);
    }

    public List<MonographType> getAll() {
        return monographTypeSqlRepository.findAll();
    }

    public Page<MonographType> getByNameContainingPaginated(String name, PageRequest pageRequest){
        return monographTypeSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }
}
