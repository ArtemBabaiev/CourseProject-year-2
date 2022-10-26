package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:32
 * @class RoleSqlRepository
 */
public interface RoleSqlRepository extends JpaRepository<Role, String> {
}
