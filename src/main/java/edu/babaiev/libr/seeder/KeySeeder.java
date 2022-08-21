package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.KeyFactory;
import edu.babaiev.libr.model.Key;
import edu.babaiev.libr.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:47
 * @class KeySeeder
 */
@Component
public class KeySeeder {
    KeyFactory factory;
    KeyService service;
    @Autowired
    public KeySeeder(KeyFactory factory, KeyService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Key> list = factory.generate();
        for (Key item:list) {
            service.create(item);
        }
    }
}
