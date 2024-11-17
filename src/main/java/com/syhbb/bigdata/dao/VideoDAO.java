package com.syhbb.bigdata.dao;

import com.syhbb.bigdata.dataObject.VideoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;



@Repository
public class VideoDAO {
    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${bilibili.collection.videos}")
    private String collection;

    @Value("${bilibili.collection.pageSize}")
    private int pageSize;

    public List<VideoDO> findAll(){
        return mongoTemplate.findAll(VideoDO.class,collection);
    }

    public VideoDO getDObyAvid(Long avid){
        Query query = new Query(Criteria.where("avid").is(avid));
        query.limit(1);
        List<VideoDO> list = mongoTemplate.find(query,VideoDO.class,collection);
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    public VideoDO addDO(VideoDO videoDO){
        return mongoTemplate.insert(videoDO,collection);
    }

    public boolean delete(Long avid){
        Query query = new Query(Criteria.where("avid").is(avid));
        return mongoTemplate.remove(query).wasAcknowledged();
    }

    public List<VideoDO> getDObyToday(){
        Query query = new Query(Criteria.where("date").is(LocalDate.now()));
        return mongoTemplate.find(query, VideoDO.class, collection);
    }

    public List<VideoDO> getDOByPage(int page){
        Pageable pageable = PageRequest.of(page, this.pageSize);
        Query query = new Query().with(pageable);
        return mongoTemplate.find(query, VideoDO.class, collection);
    }

    public List<VideoDO> searchDOByTitle(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, this.pageSize);
        Query query = new Query(Criteria.where("title").regex(".*" + keyword + ".*", "i")).with(pageable);
        return mongoTemplate.find(query, VideoDO.class, collection);
    }

}
