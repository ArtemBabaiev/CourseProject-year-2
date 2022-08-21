package edu.babaiev.libr.repository.mongo;

import edu.babaiev.libr.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 15.08.2022 23:18
 * @class BookMongoRepository
 */
public interface BookMongoRepository extends MongoRepository<Book, String> {
}
