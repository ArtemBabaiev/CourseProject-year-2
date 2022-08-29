package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Pupil;
import edu.babaiev.libr.repository.mongo.PupilMongoRepository;
import edu.babaiev.libr.repository.sql.PupilSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:13
 * @class PupilService
 */
@Service
public class PupilService {
    PupilSqlRepository pupilSqlRepository;
    PupilMongoRepository pupilMongoRepository;

    @Autowired
    public PupilService(PupilSqlRepository pupilSqlRepository, PupilMongoRepository pupilMongoRepository) {
        this.pupilSqlRepository = pupilSqlRepository;
        this.pupilMongoRepository = pupilMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Pupil create(Pupil pupil) {
        LocalDateTime time = LocalDateTime.now();
        pupil.setCreated_at(time);
        pupil.setUpdated_at(time);
        pupilMongoRepository.save(pupil);
        return pupilSqlRepository.save(pupil);
    }

    public Pupil get(String id) {
        return pupilSqlRepository.findById(id).orElse(null);
    }

    public Pupil update(Pupil pupil) {
        pupil.setUpdated_at(LocalDateTime.now());
        pupilMongoRepository.save(pupil);
        return pupilSqlRepository.save(pupil);
    }

    public void delete(String id) {
        pupilMongoRepository.deleteById(id);
        pupilSqlRepository.deleteById(id);
    }

    public List<Pupil> getAll() {
        return pupilSqlRepository.findAll();
    }
}
