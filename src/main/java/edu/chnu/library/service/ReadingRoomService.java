package edu.chnu.library.service;

import edu.chnu.library.exception.BadRequestException;
import edu.chnu.library.exception.NotFoundException;
import edu.chnu.library.model.Library;
import edu.chnu.library.model.ReadingRoom;
import edu.chnu.library.repository.mongo.ReadingRoomMongoRepository;
import edu.chnu.library.repository.sql.ReadingRoomSqlRepository;
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
 * @date 16.08.2022 23:16
 * @class ReadingRoomService
 */
@Service
public class ReadingRoomService {
    ReadingRoomSqlRepository readingRoomSqlRepository;
    ReadingRoomMongoRepository readingRoomMongoRepository;

    @Autowired
    public ReadingRoomService(ReadingRoomSqlRepository readingRoomSqlRepository, ReadingRoomMongoRepository readingRoomMongoRepository) {
        this.readingRoomSqlRepository = readingRoomSqlRepository;
        this.readingRoomMongoRepository = readingRoomMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public ReadingRoom create(ReadingRoom readingRoom) {
        LocalDateTime time = LocalDateTime.now();
        readingRoom.setCreatedAt(time);
        readingRoom.setUpdatedAt(time);
        readingRoomMongoRepository.save(readingRoom);
        return readingRoomSqlRepository.save(readingRoom);
    }

    public ReadingRoom get(String id) {
        return readingRoomSqlRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found row with id=" + id + " in database"));
    }

    public ReadingRoom update(ReadingRoom readingRoom) {
        ReadingRoom oldOne = get(readingRoom.getId());
        readingRoom.setCreatedAt(oldOne.getCreatedAt());
        readingRoom.setUpdatedAt(LocalDateTime.now());
        readingRoomMongoRepository.save(readingRoom);
        return readingRoomSqlRepository.save(readingRoom);
    }

    public void delete(String id) {
        readingRoomMongoRepository.deleteById(id);
        readingRoomSqlRepository.deleteById(id);
    }

    public List<ReadingRoom> getAll() {
        return readingRoomSqlRepository.findAll();
    }

    public Page<ReadingRoom> getByNumberContainingPaginated(String number, PageRequest pageRequest) {
        return readingRoomSqlRepository.findAllByNumberContainingIgnoreCase(number, pageRequest);
    }

    public List<ReadingRoom> getByLibrary(Library library) {
        return readingRoomSqlRepository.findAllByLibrary(library);

    }

    public List<ReadingRoom> getAllByNumberContaining(String number, Sort sort) {
        try {
            return readingRoomSqlRepository.findAllByNumberContainingIgnoreCase(number, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: з параметрами number=" + number + "; sort=" + sort.toString());
        }
    }

    public List<ReadingRoom> getAllByNumberContainingAndBetween(String number, String number2, String number3, Sort sort) {
        try {
            return readingRoomSqlRepository.findAllByNumberContainingIgnoreCaseAndNumberBetween(number, number2, number3, sort);
        } catch (Exception e) {
            throw new BadRequestException("Bad request: пошук з параметрами number=" + number + "; sort=" + sort.toString() + "; range=[" + number2 + ":" + number3 + "]");
        }
    }

    public Page<ReadingRoom> getAllPaginated(PageRequest pageRequest) {
        return readingRoomSqlRepository.findAll(pageRequest);
    }
}
