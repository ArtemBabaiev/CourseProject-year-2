package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Exemplar;
import edu.babaiev.libr.repository.mongo.ExemplarMongoRepository;
import edu.babaiev.libr.repository.sql.ExemplarSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @exemplar artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 31.08.2022 17:32
 * @class ExemplarService
 */
@Service
public class ExemplarService {
    ExemplarSqlRepository exemplarSqlRepository;
    ExemplarMongoRepository exemplarMongoRepository;

    @Autowired
    public ExemplarService(ExemplarSqlRepository exemplarSqlRepository, ExemplarMongoRepository exemplarMongoRepository) {
        this.exemplarSqlRepository = exemplarSqlRepository;
        this.exemplarMongoRepository = exemplarMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Exemplar create(Exemplar exemplar) {
        LocalDateTime time = LocalDateTime.now();
        exemplar.setCreated_at(time);
        exemplar.setUpdated_at(time);
        exemplarMongoRepository.save(exemplar);
        return exemplarSqlRepository.save(exemplar);
    }

    public Exemplar get(String id) {
        return exemplarSqlRepository.findById(id).orElse(null);
    }

    public Exemplar update(Exemplar exemplar) {
        Exemplar oldOne = get(exemplar.getId());
        exemplar.setCreated_at(oldOne.getCreated_at());
        exemplar.setUpdated_at(LocalDateTime.now());
        exemplarMongoRepository.save(exemplar);
        return exemplarSqlRepository.save(exemplar);
    }

    public void delete(String id) {
        exemplarMongoRepository.deleteById(id);
        exemplarSqlRepository.deleteById(id);
    }

    public List<Exemplar> getAll() {
        return exemplarSqlRepository.findAll();
    }

    public Page<Exemplar> getAllPaginated(PageRequest pageRequest){
        return exemplarSqlRepository.findAll(pageRequest);
    }
}
