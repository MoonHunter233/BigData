package com.syhbb.bigdata.service;

import com.syhbb.bigdata.dataObject.VideoDO;

public interface MongoVideoService {
    public void putIntoMongo(VideoDO videoDO);

    public boolean isUpdate();
}
