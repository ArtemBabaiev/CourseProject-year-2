package edu.chnu.library.repository.mongo;

import edu.chnu.library.model.Scientist;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 19.08.2022 22:50
 * @class ScientistMongoRepository
 */
public interface ScientistMongoRepository extends MongoRepository<Scientist, String> {
}
