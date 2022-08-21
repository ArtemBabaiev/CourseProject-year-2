package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.RecordFactory;
import edu.babaiev.libr.model.Record;
import edu.babaiev.libr.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:50
 * @class RecordSeeder
 */
@Component
public class RecordSeeder {
    RecordFactory factory;
    RecordService service;
    @Autowired
    public RecordSeeder(RecordFactory factory, RecordService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Record> list = factory.generate();
        for (Record item:list) {
            service.create(item);
        }
    }
}
