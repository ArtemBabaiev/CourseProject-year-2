package edu.chnu.library.repository.mongo;

import edu.chnu.library.model.PeriodicalType;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 16.08.2022 20:56
 * @class PeriodicalTypeMongoRepository
 */
public interface PeriodicalTypeMongoRepository extends MongoRepository<PeriodicalType, String> {
}
