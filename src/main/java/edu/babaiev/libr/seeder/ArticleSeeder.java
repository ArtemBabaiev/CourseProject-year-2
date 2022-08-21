package edu.babaiev.libr.seeder;

import edu.babaiev.libr.factory.ArticleFactory;
import edu.babaiev.libr.model.Article;
import edu.babaiev.libr.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 20.08.2022 03:40
 * @class ArticleSeeder
 */
@Component
public class ArticleSeeder {
    ArticleFactory factory;
    ArticleService service;
    @Autowired
    public ArticleSeeder(ArticleFactory factory, ArticleService service) {
        this.factory = factory;
        this.service = service;
    }

    public void Seed(){
        List<Article> list = factory.generate();
        for (Article item:list) {
            service.create(item);
        }
    }
}
