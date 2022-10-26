package edu.chnu.library.repository.mongo;

import edu.chnu.library.model.Exemplar;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 31.08.2022 17:22
 * @class ExemplarMongoRepository
 */
public interface ExemplarMongoRepository extends MongoRepository<Exemplar, String> {
}
