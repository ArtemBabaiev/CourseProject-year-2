package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:32
 * @class RoleSqlRepository
 */
public interface RoleSqlRepository extends JpaRepository<Role, String> {
}
