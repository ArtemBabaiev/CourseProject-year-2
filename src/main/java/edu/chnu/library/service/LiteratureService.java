package edu.chnu.library.service;

import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Literature;
import edu.chnu.library.repository.mongo.LiteratureMongoRepository;
import edu.chnu.library.repository.sql.LiteratureSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return literatureSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
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

    public Page<Literature> getByNameContainingPaginated(String name, PageRequest pageRequest) {
        return literatureSqlRepository.findAllByNameContainingIgnoreCase(name, pageRequest);
    }

    @Transactional
    public List<Literature> getCreatedInPeriod(String lowerDate, String upperDate) {
        return literatureSqlRepository.getLiteratureCreatedInPeriod(lowerDate, upperDate);
    }

    @Transactional
    public List<Literature> getUsedByReaderInPeriod(String readerId, String lowerDate, String upperDate) {
        return literatureSqlRepository.getLiteratureUsedByReaderInPeriod(readerId, lowerDate, upperDate);
    }

    @Transactional
    public List<Literature> getContainsAuthor(String authorId) {
        return literatureSqlRepository.getLitrContainsAuthor(authorId);
    }

    @Transactional
    public List<Literature> getContainsWriting(String writingId) {
        return literatureSqlRepository.getLitrContainsWriting(writingId);
    }
}
