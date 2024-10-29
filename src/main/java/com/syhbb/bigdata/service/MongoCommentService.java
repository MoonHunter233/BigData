package com.syhbb.bigdata.service;

import com.syhbb.bigdata.dataObject.CommentDO;

public interface MongoCommentService {
    public void putIntoMongo(CommentDO commentDO);
}
