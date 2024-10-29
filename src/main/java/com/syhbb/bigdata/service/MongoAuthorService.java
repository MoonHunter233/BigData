package com.syhbb.bigdata.service;

import com.syhbb.bigdata.dataObject.AuthorDO;

public interface MongoAuthorService {
    public void putIntoMongo(AuthorDO authorDO);
}
