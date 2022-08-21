package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.MonographTypeFactory;
import edu.babaiev.libr.model.MonographType;
import edu.babaiev.libr.service.MonographTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:48
 * @class MonographTypeSeeder
 */
@Component
public class MonographTypeSeeder {
    MonographTypeFactory factory;
    MonographTypeService service;
    @Autowired
    public MonographTypeSeeder(MonographTypeFactory factory, MonographTypeService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<MonographType> list = factory.generate();
        for (MonographType item:list) {
            service.create(item);
        }
    }
}
