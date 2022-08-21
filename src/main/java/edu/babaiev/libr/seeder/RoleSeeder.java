package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.RoleFactory;
import edu.babaiev.libr.model.Role;
import edu.babaiev.libr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:50
 * @class RoleSeeder
 */
@Component
public class RoleSeeder {
    RoleFactory factory;
    RoleService service;
    @Autowired
    public RoleSeeder(RoleFactory factory, RoleService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Role> list = factory.generate();
        for (Role item:list) {
            service.create(item);
        }
    }
}
