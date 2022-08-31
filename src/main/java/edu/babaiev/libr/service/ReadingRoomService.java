package edu.babaiev.libr.service;

import edu.babaiev.libr.model.ReadingRoom;
import edu.babaiev.libr.repository.mongo.ReadingRoomMongoRepository;
import edu.babaiev.libr.repository.sql.ReadingRoomSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        readingRoom.setCreated_at(time);
        readingRoom.setUpdated_at(time);
        readingRoomMongoRepository.save(readingRoom);
        return readingRoomSqlRepository.save(readingRoom);
    }

    public ReadingRoom get(String id) {
        return readingRoomSqlRepository.findById(id).orElse(null);
    }

    public ReadingRoom update(ReadingRoom readingRoom) {
        ReadingRoom oldOne = get(readingRoom.getId());
        readingRoom.setCreated_at(oldOne.getCreated_at());
        readingRoom.setUpdated_at(LocalDateTime.now());
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
}
