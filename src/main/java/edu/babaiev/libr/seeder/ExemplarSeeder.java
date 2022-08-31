package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.ExemplarFactory;
import edu.babaiev.libr.model.Exemplar;
import edu.babaiev.libr.service.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 31.08.2022 18:01
 * @class ExemplarSeeder
 */
@Component
public class ExemplarSeeder {
    ExemplarFactory factory;
    ExemplarService service;
    @Autowired
    public ExemplarSeeder(ExemplarFactory factory, ExemplarService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Exemplar> list = factory.generate();
        for (Exemplar item:list) {
            service.create(item);
        }
    }
}
