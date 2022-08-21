package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.PeriodicalTypeFactory;
import edu.babaiev.libr.model.PeriodicalType;
import edu.babaiev.libr.service.PeriodicalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:48
 * @class PeriodicalTypeSeeder
 */
@Component
public class PeriodicalTypeSeeder {
    PeriodicalTypeFactory factory;
    PeriodicalTypeService service;
    @Autowired
    public PeriodicalTypeSeeder(PeriodicalTypeFactory factory, PeriodicalTypeService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<PeriodicalType> list = factory.generate();
        for (PeriodicalType item:list) {
            service.create(item);
        }
    }
}
