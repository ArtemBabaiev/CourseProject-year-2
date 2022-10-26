package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Writing;
import edu.chnu.library.repository.mongo.WritingMongoRepository;
import edu.chnu.library.repository.sql.WritingSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        return writingSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
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

    public Page<Writing> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return writingSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    @Transactional
    public List<Writing> getTop10() {
        List<String> ids = writingSqlRepository.getMostPopularWritings();
        List<Writing> writings = new ArrayList<>();
        for (String id : ids) {
            writings.add(writingSqlRepository.findById(id).orElse(null));
        }
        return writings;
    }

    public List<Writing> getAllByNameContaining(String name, Sort sort) {
        try {
            return writingSqlRepository.findAllByNameContainingIgnoreCase(name, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами name=" + name + "; sort=" + sort.toString());
        }
    }

    public List<Writing> getAllByNameContainingAndBetween(String name, String name2, String name3, Sort sort) {
        try {
            return writingSqlRepository.findAllByNameContainingIgnoreCaseAndNameBetween(name, name2, name3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами name=" + name + "; sort=" + sort.toString() + "; range=[" + name2 + ":" + name3 + "]");
        }
    }

    public Page<Writing> getAllPaginated(PageRequest pageRequest) {
        return writingSqlRepository.findAll(pageRequest);
    }
}
