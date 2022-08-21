package edu.babaiev.libr.factory;

import edu.babaiev.libr.model.Library;
import edu.babaiev.libr.model.ReadingRoom;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:15
 * @class ReadingRoomFactory
 */
@Component
public class ReadingRoomFactory {
    public List<ReadingRoom> generate() {
        List<ReadingRoom> readingRooms = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.READING_ROOM.getValue(); i++) {
            readingRooms.add(
                    new ReadingRoom(i + "",
                            faker.random().hex(2),
                            new Library(faker.random().nextInt(1, QuantityConfig.LIBRARY.getValue()) + ""),
                            LocalDateTime.now(),
                            LocalDateTime.now())
            );
        }
        return readingRooms;
    }
}
