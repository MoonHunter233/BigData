package com.syhbb.bigdata.util;

import com.syhbb.bigdata.spiderData.DO.comment.CommentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDataDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    //TODO mongoDB创建数据库comments -> row_comments_data
    @Value("${bilibili.collection.rowCommentsData}")
    private String collection;

    //CommentData module
    //TODO comment CRUD

    public List<CommentData> insertEach(List<CommentData> commentData){
        return (List<CommentData>) mongoTemplate.insert(commentData, collection);
    }
}
