package edu.babaiev.libr.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 14.08.2022 22:39
 * @class Configuration
 */
@org.springframework.context.annotation.Configuration
@EnableJpaRepositories(basePackages = "edu.babaiev.libr.repository.sql")
@EnableMongoRepositories(basePackages = "edu.babaiev.libr.repository.mongo")
public class Configuration {
}
