package com.syhbb.bigdata.dao;

import com.syhbb.bigdata.dataObject.StateDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StateDAO {
    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${bilibili.collection.state}")
    private String collection;

    public List<StateDO> findAll() {
        return mongoTemplate.findAll(StateDO.class);
    }

    public StateDO addDO(StateDO stateDO) {
        return mongoTemplate.insert(stateDO, collection);
    }

    public List<StateDO> getByAvid(long avid) {
        Query query = new Query(Criteria.where("avid").is(avid));
        query.limit(1);
        return mongoTemplate.find(query, StateDO.class, collection);
    }

    public boolean modifyByAvid(StateDO stateDO) {
        Query query = new Query(Criteria.where("avid").is(stateDO.getAvid()));
        Update update = new Update();
        update.set("view", stateDO.getView());
        update.set("danMu", stateDO.getDanMu());
        update.set("comment", stateDO.getComment());
        update.set("favorite", stateDO.getFavorite());
        update.set("coin", stateDO.getCoin());
        update.set("share", stateDO.getShare());
        update.set("nowRank", stateDO.getNowRank());
        update.set("hisRank", stateDO.getHisRank());
        update.set("like", stateDO.getLike());
        update.set("dislike", stateDO.getDislike());
        return mongoTemplate.updateFirst(query, update, collection).wasAcknowledged();
    }
}
