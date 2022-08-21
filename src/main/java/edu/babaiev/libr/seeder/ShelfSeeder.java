package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.ShelfFactory;
import edu.babaiev.libr.model.Shelf;
import edu.babaiev.libr.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:50
 * @class ShelfSeeder
 */
@Component
public class ShelfSeeder {
    ShelfFactory factory;
    ShelfService service;
    @Autowired
    public ShelfSeeder(ShelfFactory factory, ShelfService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Shelf> list = factory.generate();
        for (Shelf item:list) {
            service.create(item);
        }
    }
}
