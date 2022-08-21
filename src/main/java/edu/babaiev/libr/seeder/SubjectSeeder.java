package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.SubjectFactory;
import edu.babaiev.libr.model.Subject;
import edu.babaiev.libr.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:51
 * @class SubjectSeeder
 */
@Component
public class SubjectSeeder {
    SubjectFactory factory;
    SubjectService service;
    @Autowired
    public SubjectSeeder(SubjectFactory factory, SubjectService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Subject> list = factory.generate();
        for (Subject item:list) {
            service.create(item);
        }
    }
}
