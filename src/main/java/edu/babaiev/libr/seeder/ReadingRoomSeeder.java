package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.ReadingRoomFactory;
import edu.babaiev.libr.model.ReadingRoom;
import edu.babaiev.libr.service.ReadingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:49
 * @class ReadingRoomSeeder
 */
@Component
public class ReadingRoomSeeder {
    ReadingRoomFactory factory;
    ReadingRoomService service;
    @Autowired
    public ReadingRoomSeeder(ReadingRoomFactory factory, ReadingRoomService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<ReadingRoom> list = factory.generate();
        for (ReadingRoom item:list) {
            service.create(item);
        }
    }
}
