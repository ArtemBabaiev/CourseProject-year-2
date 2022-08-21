package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.WritingFactory;
import edu.babaiev.libr.model.Writing;
import edu.babaiev.libr.service.WritingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:51
 * @class WritingSeeder
 */
@Component
public class WritingSeeder {
    WritingFactory factory;
    WritingService service;
    @Autowired
    public WritingSeeder(WritingFactory factory, WritingService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Writing> list = factory.generate();
        for (Writing item:list) {
            service.create(item);
        }
    }
}
