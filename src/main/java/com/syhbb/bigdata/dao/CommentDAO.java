package com.syhbb.bigdata.dao;

import com.syhbb.bigdata.dataObject.CommentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public class CommentDAO {
    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${bilibili.collection.comments}")
    private String collection;

    public List<CommentDO> findAll(){
        return mongoTemplate.findAll(CommentDO.class,collection);
    }

    public List<CommentDO> findByCommentId(long commentId){
        Query query = new Query(Criteria.where("commentId").is(commentId));
        return mongoTemplate.find(query, CommentDO.class, collection);
    }

    public CommentDO addDO(CommentDO commentDO) {
        return mongoTemplate.insert(commentDO, collection);
    }

    public boolean modifyByCommentId(CommentDO commentDO) {
        Query query = new Query(Criteria.where("commentId").is(commentDO.getCommentId()));
        Update update = new Update();
        update.set("like", commentDO.getLike());
        update.set("content", commentDO.getContent());
        return mongoTemplate.updateFirst(query,update,collection).wasAcknowledged();
    }
}
