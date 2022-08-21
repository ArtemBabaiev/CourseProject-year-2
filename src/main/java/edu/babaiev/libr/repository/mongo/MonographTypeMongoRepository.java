package edu.babaiev.libr.repository.mongo;

import edu.babaiev.libr.model.MonographType;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:51
 * @class MonographTypeMongoRepository
 */
public interface MonographTypeMongoRepository extends MongoRepository<MonographType, String> {
}
