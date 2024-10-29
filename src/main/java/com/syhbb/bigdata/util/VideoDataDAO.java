package com.syhbb.bigdata.util;

import com.syhbb.bigdata.spiderData.DO.comment.CommentData;
import com.syhbb.bigdata.spiderData.DO.video.VideoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoDataDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    //TODO rename collection -> row_videos_data
    @Value("${bilibili.collection.rowVideosData}")
    private String videosCollection;

    //VideoData module
    //TODO video CRUD

    public List<VideoData> insertEach(List<VideoData> videoData){
        return (List<VideoData>) mongoTemplate.insert(videoData, videosCollection);
    }
    public VideoData insertOne(VideoData videoData){
        return mongoTemplate.insert(videoData, videosCollection);
    }


}
