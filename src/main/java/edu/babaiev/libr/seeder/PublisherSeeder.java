package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.PublisherFactory;
import edu.babaiev.libr.model.Publisher;
import edu.babaiev.libr.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:49
 * @class PublisherSeeder
 */
@Component
public class PublisherSeeder {
    PublisherFactory factory;
    PublisherService service;
    @Autowired
    public PublisherSeeder(PublisherFactory factory, PublisherService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Publisher> list = factory.generate();
        for (Publisher item:list) {
            service.create(item);
        }
    }
}
