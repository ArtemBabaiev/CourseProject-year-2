package edu.chnu.library.repository.mongo;

import edu.chnu.library.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:50
 * @class GenreMongoRepository
 */
public interface GenreMongoRepository extends MongoRepository<Genre, String> {
}
