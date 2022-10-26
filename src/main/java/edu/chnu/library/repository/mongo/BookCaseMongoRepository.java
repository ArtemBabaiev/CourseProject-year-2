package edu.chnu.library.repository.mongo;

import edu.chnu.library.model.BookCase;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:33
 * @class BookCaseMongoRepository
 */
public interface BookCaseMongoRepository extends MongoRepository<BookCase, String> {
}
