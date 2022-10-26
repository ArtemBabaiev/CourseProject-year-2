package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Adult;
import edu.chnu.library.repository.mongo.AdultMongoRepository;
import edu.chnu.library.repository.sql.AdultSqlRepository;
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
        adult.setCreatedAt(time);
        adult.setUpdatedAt(time);
        adultMongoRepository.save(adult);
        return adultSqlRepository.save(adult);
    }

    public Adult get(String id) {
        return adultSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Adult update(Adult adult) {
        Adult oldOne = get(adult.getId());
        adult.setCreatedAt(oldOne.getCreatedAt());
        adult.setUpdatedAt(LocalDateTime.now());
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

    public List<Adult> getAllByLastNameContaining(String lastName, Sort sort) {
        try {
            return adultSqlRepository.findAllByLastNameContainingIgnoreCase(lastName, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук дорослого з параметрами last_name=" + lastName + "; sort=" + sort.toString());
        }
    }

    public List<Adult> getAllByLastNameContainingAndBetween(String lastName, String lastName2, String lastName3, Sort sort) {
        try {
            return adultSqlRepository.findAllByLastNameContainingIgnoreCaseAndLastNameBetween(lastName, lastName2, lastName3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук дорослого з параметрами last_name=" + lastName + "; sort=" + sort.toString() + "; range=[" + lastName2 + ":" + lastName3 + "]");
        }
    }

    public Page<Adult> getAllPaginated(PageRequest pageRequest){
        return adultSqlRepository.findAll(pageRequest);
    }

}
