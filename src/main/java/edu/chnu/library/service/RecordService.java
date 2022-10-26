package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.ExemplarInUseException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Exemplar;
import edu.chnu.library.model.Record;
import edu.chnu.library.repository.mongo.RecordMongoRepository;
import edu.chnu.library.repository.sql.RecordSqlRepository;
import edu.chnu.library.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:17
 * @class RecordService
 */
@Service
public class RecordService {
    RecordSqlRepository recordSqlRepository;
    RecordMongoRepository recordMongoRepository;
    ExemplarService exemplarService;
    AuthService authService;

    @Autowired
    public RecordService(RecordSqlRepository recordSqlRepository, RecordMongoRepository recordMongoRepository,
                         ExemplarService exemplarService, AuthService authService) {
        this.recordSqlRepository = recordSqlRepository;
        this.recordMongoRepository = recordMongoRepository;
        this.exemplarService = exemplarService;
        this.authService = authService;
    }

    //@PostConstruct
    void init() {
    }

    public Record create(Record record) {
        LocalDateTime time = LocalDateTime.now();
        record.setCreatedAt(time);
        record.setUpdatedAt(time);
        recordMongoRepository.save(record);
        return recordSqlRepository.save(record);
    }

    public Record get(String id) {
        return recordSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public Record update(Record record) {
        Record oldOne = get(record.getId());
        record.setCreatedAt(oldOne.getCreatedAt());
        record.setUpdatedAt(LocalDateTime.now());
        recordMongoRepository.save(record);
        return recordSqlRepository.save(record);
    }

    public void delete(String id) {
        recordMongoRepository.deleteById(id);
        recordSqlRepository.deleteById(id);
    }

    public List<Record> getAll() {
        return recordSqlRepository.findAll();
    }

    public Page<Record> getAllByReaderLastNameContainingPaginated(String lastName, PageRequest pageRequest) {
        return recordSqlRepository.findAllByReader_LastNameContainingIgnoreCase(lastName, pageRequest);
    }

    public Record prepareRecordForExemplar(String exemplarId) {
        try {
            Exemplar exemplar = exemplarService.getForRecordById(exemplarId);
            if (!exemplar.getShelf().getBookCase().getReadingRoom().equals(authService.getCurrentEmployee().getReadingRoom())) {
                return null;
            }
            Record record = new Record();
            record.setExemplar(exemplar);
            return record;
        } catch (ExemplarInUseException e) {
            return null;
        }
    }

    public Record createRecord(Record record, String exemplarId) {
        try {
            Exemplar forRecordById = exemplarService.getForRecordById(exemplarId);
            if (!forRecordById.getShelf().getBookCase().getReadingRoom().equals(authService.getCurrentEmployee().getReadingRoom())) {
                return null;
            }
            record.setExemplar(forRecordById);
            record.setLendAt(LocalDateTime.now());
            record.setLendBy(authService.getCurrentEmployee());
            exemplarService.giveExemplar(forRecordById);
            create(record);
            return record;
        } catch (ExemplarInUseException e) {
            return null;
        }
    }

    public Record returnRecord(String recordId) {
        Record record = get(recordId);
        exemplarService.returnExemplar(record.getExemplar());
        record.setReturnedAt(LocalDateTime.now());
        record.setAcceptedBy(authService.getCurrentEmployee());
        update(record);
        return record;
    }

    @Transactional
    public Object getCountOfServedReaders() {
        return recordSqlRepository.getReadersCountForAllEmployees();
    }

    public List<Record> getAllByReaderLastNameContaining(String lastName, Sort sort) {
        try {
            return recordSqlRepository.findAllByReader_LastNameContainingIgnoreCase(lastName, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами lastName=" + lastName + "; sort=" + sort.toString());
        }
    }

    public List<Record> getAllByReaderLastNameContainingAndBetween(String lastName, String lastName2, String lastName3, Sort sort) {
        try {
            return recordSqlRepository.findAllByReader_LastNameContainingIgnoreCaseAndReader_LastNameBetween(lastName, lastName2, lastName3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами lastName=" + lastName + "; sort=" + sort.toString() + "; range=[" + lastName2 + ":" + lastName3 + "]");
        }
    }

    public Page<Record> getAllPaginated(PageRequest pageRequest) {
        return recordSqlRepository.findAll(pageRequest);
    }
}
