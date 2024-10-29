package com.syhbb.bigdata.dao;

import com.syhbb.bigdata.dataObject.AuthorDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDAO {
    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${bilibili.collection.authors}")
    private String collection;

    public List<AuthorDO> findAll() {
        return mongoTemplate.findAll(AuthorDO.class, collection);
    }

    public AuthorDO addDO(AuthorDO authorDO) {
        return mongoTemplate.insert(authorDO, collection);
    }

    public List<AuthorDO> getById(long id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.find(query, AuthorDO.class, collection);
    }

    public List<AuthorDO> getByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.find(query, AuthorDO.class, collection);
    }

    public boolean modifyById(AuthorDO authorDO) {
        Query query = new Query(Criteria.where("id").is(authorDO.getId()));
        Update update = new Update();
        update.set("name", authorDO.getName());
        update.set("avatar", authorDO.getAvatar());
        return mongoTemplate.updateFirst(query, update, collection).wasAcknowledged();
    }
}
