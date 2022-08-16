package edu.babaiev.libr.repository.sql;

import edu.babaiev.libr.model.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:20
 * @class PupilSqlRepository
 */
public interface PupilSqlRepository extends JpaRepository<Pupil, String> {
}
