package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.WrittenOffFactory;
import edu.babaiev.libr.model.WrittenOff;
import edu.babaiev.libr.service.WrittenOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:51
 * @class WrittenOffSeeder
 */
@Component
public class WrittenOffSeeder {
    WrittenOffFactory factory;
    WrittenOffService service;
    @Autowired
    public WrittenOffSeeder(WrittenOffFactory factory, WrittenOffService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<WrittenOff> list = factory.generate();
        for (WrittenOff item:list) {
            service.create(item);
        }
    }
}
