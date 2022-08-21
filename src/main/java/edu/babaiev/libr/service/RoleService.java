package edu.babaiev.libr.service;

import edu.babaiev.libr.model.Role;
import edu.babaiev.libr.repository.mongo.RoleMongoRepository;
import edu.babaiev.libr.repository.sql.RoleSqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 23:19
 * @class RoleService
 */
@Service
public class RoleService {
    RoleSqlRepository roleSqlRepository;
    RoleMongoRepository roleMongoRepository;

    @Autowired
    public RoleService(RoleSqlRepository roleSqlRepository, RoleMongoRepository roleMongoRepository) {
        this.roleSqlRepository = roleSqlRepository;
        this.roleMongoRepository = roleMongoRepository;
    }

    //@PostConstruct
    void init() {
    }

    public Role create(Role role) {
        role.setCreated_at(LocalDateTime.now());
        roleMongoRepository.save(role);
        return roleSqlRepository.save(role);
    }

    public Role get(String id) {
        return roleSqlRepository.findById(id).orElse(null);
    }

    public Role update(Role role) {
        role.setUpdated_at(LocalDateTime.now());
        roleMongoRepository.save(role);
        return roleSqlRepository.save(role);
    }

    public void delete(String id) {
        roleMongoRepository.deleteById(id);
        roleSqlRepository.deleteById(id);
    }

    public List<Role> getAll() {
        return roleSqlRepository.findAll();
    }
}
