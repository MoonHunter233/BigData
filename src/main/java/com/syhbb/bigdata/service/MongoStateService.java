package com.syhbb.bigdata.service;

import com.syhbb.bigdata.dataObject.StateDO;

public interface MongoStateService {
    public void putIntoMongo(StateDO stateDO);
}
