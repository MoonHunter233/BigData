package com.syhbb.bigdata.service;

public interface SpiderService {
    public boolean setPopularInMongo(int pageNumber, int pageSize);
    public boolean setCommentInMongo(long avid, int sort, int pageNumber, int pageSize);
    public String getDataSourceSize(int pageNumber, int pageSize);
}
