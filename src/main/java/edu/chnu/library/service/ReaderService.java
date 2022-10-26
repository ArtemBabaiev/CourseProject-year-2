package edu.chnu.library.service;

import edu.chnu.library.dto.ReaderActivityDTO;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Reader;
import edu.chnu.library.repository.mongo.ReaderMongoRepository;
import edu.chnu.library.repository.sql.ReaderSqlRepository;
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
 * @date 16.08.2022 23:15
 * @class ReaderService
 */
@Service
public class ReaderService {
    ReaderSqlRepository readerSqlRepository;
    ReaderMongoRepository readerMongoRepository;

    @Autowired
    public ReaderService(ReaderSqlRepository readerSqlRepository, ReaderMongoRepository readerMongoRepository) {
        this.readerSqlRepository = readerSqlRepository;
        this.readerMongoRepository = readerMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Reader create(Reader reader) {
        LocalDateTime time = LocalDateTime.now();
        reader.setCreatedAt(time);
        reader.setUpdatedAt(time);
        readerMongoRepository.save(reader);
        return readerSqlRepository.save(reader);
    }

    public Reader get(String id) {
        return readerSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Reader update(Reader reader) {
        Reader oldOne = get(reader.getId());
        reader.setCreatedAt(oldOne.getCreatedAt());
        reader.setUpdatedAt(LocalDateTime.now());
        readerMongoRepository.save(reader);
        return readerSqlRepository.save(reader);
    }

    public void delete(String id) {
        readerMongoRepository.deleteById(id);
        readerSqlRepository.deleteById(id);
    }

    public List<Reader> getAll() {
        return readerSqlRepository.findAll();
    }

    public Page<Reader> getByLastNameContainingPaginated(String lastname, PageRequest pageRequest) {
        return readerSqlRepository.findAllByLastNameContaining(lastname, pageRequest);
    }

    @Transactional
    public List<Reader> getReadersThatLendLiterature(String literatureId) {
        return readerSqlRepository.getReadersByLendedLiterature(literatureId);
    }

    @Transactional
    public List<ReaderActivityDTO> getWithNoActivityInPeriod(String lowerDate, String upperDate) {
        return readerSqlRepository.getReadersWithNoActivityInPeriod(lowerDate, upperDate);
    }

    @Transactional
    public List<Reader> getWithOverdue() {
        return readerSqlRepository.getReadersWithOverdue();
    }
}
