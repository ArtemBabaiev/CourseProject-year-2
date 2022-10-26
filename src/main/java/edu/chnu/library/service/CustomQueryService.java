package edu.chnu.library.service;

import antlr.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClientSettings;
import edu.chnu.library.gson.LocalDateSerializer;
import edu.chnu.library.gson.LocalDateTimeSerializer;
import org.bson.Document;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author artem
 * @version: 1.0.0
 * @project CourseProject-year-2
 * @date 21.10.2022 11:59
 * @class CustomQueryService
 */
@Service
public class CustomQueryService {
    EntityManager em;
    MongoTemplate mt;
    Gson gson;
    final DocumentCodec codec;

    @Autowired
    public CustomQueryService(EntityManager em, MongoTemplate mt) {
        this.em = em;
        this.mt = mt;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gson = gsonBuilder.create();
        CodecRegistry codecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        codec = new DocumentCodec(codecRegistry, new BsonTypeClassMap());
    }

    public String sqlQueryHandler(String query) {
        Object resultList = em.createNativeQuery(query).getResultList();
        return gson.toJson(resultList);
    }

    @Transactional
    public String sqlGetNames(String query) {
        String tableName = "new_tbl" + UUID.randomUUID().toString().replace("-", "");
        em.createNativeQuery("CREATE TEMPORARY TABLE " + tableName + " " + StringUtils.stripBack(query, ";") + " limit 0").executeUpdate();
        List description = em.createNativeQuery("desc " + tableName).getResultList();
        em.createNativeQuery("DROP TABLE " + tableName).executeUpdate();
        return gson.toJson(description);
    }

    public String mongoQueryHandler(String query) {
        Document document = mt.executeCommand(query);
        return document.toJson(codec);
    }

}
