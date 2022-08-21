package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.CollectionTypeFactory;
import edu.babaiev.libr.model.CollectionType;
import edu.babaiev.libr.service.CollectionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:45
 * @class CollectionTypeSeeder
 */
@Component
public class CollectionTypeSeeder {
    CollectionTypeFactory factory;
    CollectionTypeService service;
    @Autowired
    public CollectionTypeSeeder(CollectionTypeFactory factory, CollectionTypeService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<CollectionType> list = factory.generate();
        for (CollectionType item:list) {
            service.create(item);
        }
    }
}
