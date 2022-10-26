package edu.chnu.library.repository.mongo;

import edu.chnu.library.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:27
 * @class AuthorMongoRepository
 */
public interface AuthorMongoRepository extends MongoRepository<Author, String> {
}
