package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Pupil;
import edu.chnu.library.model.Pupil;
import edu.chnu.library.repository.mongo.PupilMongoRepository;
import edu.chnu.library.repository.sql.PupilSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        pupil.setCreatedAt(time);
        pupil.setUpdatedAt(time);
        pupilMongoRepository.save(pupil);
        return pupilSqlRepository.save(pupil);
    }

    public Pupil get(String id) {
        return pupilSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Pupil update(Pupil pupil) {
        Pupil oldOne = get(pupil.getId());
        pupil.setCreatedAt(oldOne.getCreatedAt());
        pupil.setUpdatedAt(LocalDateTime.now());
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

    public List<Pupil> getAllByLastNameContaining(String lastName, Sort sort) {
        try {
            return pupilSqlRepository.findAllByLastNameContainingIgnoreCase(lastName, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук дорослого з параметрами last_name=" + lastName + "; sort=" + sort.toString());
        }
    }

    public List<Pupil> getAllByLastNameContainingAndBetween(String lastName, String lastName2, String lastName3, Sort sort) {
        try {
            return pupilSqlRepository.findAllByLastNameContainingIgnoreCaseAndLastNameBetween(lastName, lastName2, lastName3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук дорослого з параметрами last_name=" + lastName + "; sort=" + sort.toString() + "; range=[" + lastName2 + ":" + lastName3 + "]");
        }
    }

    public Page<Pupil> getAllPaginated(PageRequest pageRequest){
        return pupilSqlRepository.findAll(pageRequest);
    }
}
