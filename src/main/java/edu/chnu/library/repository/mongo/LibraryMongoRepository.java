package edu.chnu.library.repository.mongo;

import edu.chnu.library.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:34
 * @class LibraryMongoRepository
 */
public interface LibraryMongoRepository extends MongoRepository<Library, String> {
}
