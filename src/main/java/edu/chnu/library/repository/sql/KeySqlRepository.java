package edu.chnu.library.repository.sql;

import edu.chnu.library.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:30
 * @class KeySqlRepository
 */
public interface KeySqlRepository extends JpaRepository<Key, String> {
    Key findKeyByLogin(String login);
}
