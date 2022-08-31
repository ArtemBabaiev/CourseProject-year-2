package edu.babaiev.libr.factory;

import com.github.javafaker.Faker;
import edu.babaiev.libr.model.Article;
import edu.babaiev.libr.model.Author;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 19.08.2022 23:16
 * @class ArticleFactory
 */
@Component
public class ArticleFactory {
    public List<Article> generate() {
        List<Article> articles = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= QuantityConfig.ARTICLE.getValue(); i++) {
            if (faker.random().nextBoolean()) {
                articles.add(
                        new Article(i + "",
                                faker.book().title(),
                                new HashSet<Author>(Arrays.asList(new Author(faker.random().nextInt(1, QuantityConfig.AUTHOR.getValue()) + ""))),
                                faker.lorem().toString(),
                                LocalDateTime.now(),
                                LocalDateTime.now())
                );
            } else {
                articles.add(
                        new Article(i + "",
                                faker.book().title(),
                                new HashSet<Author>(
                                        Arrays.asList(
                                                new Author(faker.random().nextInt(1, QuantityConfig.AUTHOR.getValue()) + ""),
                                                new Author(faker.random().nextInt(1, QuantityConfig.AUTHOR.getValue()) + "")
                                        )
                                ),
                                faker.lorem().toString(),
                                LocalDateTime.now(),
                                LocalDateTime.now())
                );
            }
        }
        return articles;
    }
}
