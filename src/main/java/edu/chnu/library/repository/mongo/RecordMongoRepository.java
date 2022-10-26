package edu.chnu.library.repository.mongo;

import edu.chnu.library.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:31
 * @class RecordMongoRepository
 */
public interface RecordMongoRepository extends MongoRepository<Record, String> {
}
