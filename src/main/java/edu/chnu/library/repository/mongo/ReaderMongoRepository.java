package edu.chnu.library.repository.mongo;

import edu.chnu.library.model.Reader;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:21
 * @class ReaderMongoRepository
 */
public interface ReaderMongoRepository extends MongoRepository<Reader, String> {
}
