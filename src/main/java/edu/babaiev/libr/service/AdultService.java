package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Adult;
import edu.babaiev.libr.repository.mongo.AdultMongoRepository;
import edu.babaiev.libr.repository.sql.AdultSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 22:45
 * @class AdultService
 */
@Service
public class AdultService {
    AdultSqlRepository adultSqlRepository;
    AdultMongoRepository adultMongoRepository;

    @Autowired
    public AdultService(AdultSqlRepository adultSqlRepository, AdultMongoRepository adultMongoRepository) {
        this.adultSqlRepository = adultSqlRepository;
        this.adultMongoRepository = adultMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Adult create(Adult adult) {
        LocalDateTime time = LocalDateTime.now();
        adult.setCreated_at(time);
        adult.setUpdated_at(time);
        adultMongoRepository.save(adult);
        return adultSqlRepository.save(adult);
    }

    public Adult get(String id) {
        return adultSqlRepository.findById(id).orElse(null);
    }

    public Adult update(Adult adult) {
        Adult oldOne = get(adult.getId());
        adult.setCreated_at(oldOne.getCreated_at());
        adult.setUpdated_at(LocalDateTime.now());
        adultMongoRepository.save(adult);
        return adultSqlRepository.save(adult);
    }

    public void delete(String id) {
        adultMongoRepository.deleteById(id);
        adultSqlRepository.deleteById(id);
    }

    public List<Adult> getAll() {
        return adultSqlRepository.findAll();
    }
}
