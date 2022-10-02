package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Literature;
import edu.babaiev.libr.repository.mongo.LiteratureMongoRepository;
import edu.babaiev.libr.repository.sql.LiteratureSqlRepository;
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
 * @date 16.08.2022 23:04
 * @class LiteratureService
 */
@Service
public class LiteratureService {
    LiteratureSqlRepository literatureSqlRepository;
    LiteratureMongoRepository literatureMongoRepository;

    @Autowired
    public LiteratureService(LiteratureSqlRepository literatureSqlRepository, LiteratureMongoRepository literatureMongoRepository) {
        this.literatureSqlRepository = literatureSqlRepository;
        this.literatureMongoRepository = literatureMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Literature create(Literature literature) {
        LocalDateTime time = LocalDateTime.now();
        literature.setCreatedAt(time);
        literature.setUpdatedAt(time);
        literatureMongoRepository.save(literature);
        return literatureSqlRepository.save(literature);
    }

    public Literature get(String id) {
        return literatureSqlRepository.findById(id).orElse(null);
    }

    public Literature update(Literature literature) {
        Literature oldOne = get(literature.getId());
        literature.setCreatedAt(oldOne.getCreatedAt());
        literature.setUpdatedAt(LocalDateTime.now());
        literatureMongoRepository.save(literature);
        return literatureSqlRepository.save(literature);
    }

    public void delete(String id) {
        literatureMongoRepository.deleteById(id);
        literatureSqlRepository.deleteById(id);
    }

    public List<Literature> getAll() {
        return literatureSqlRepository.findAll();
    }

    public Page<Literature> getByNameContainingPaginated(String name ,PageRequest pageRequest){
        return literatureSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

}
