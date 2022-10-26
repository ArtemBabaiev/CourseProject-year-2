package edu.chnu.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 14.08.2022 22:39
 * @class Configuration
 */
@Configuration
@EnableJpaRepositories(basePackages = "edu.chnu.library.repository.sql")
@EnableMongoRepositories(basePackages = "edu.chnu.library.repository.mongo")
public class RepositoryConfig {
}
