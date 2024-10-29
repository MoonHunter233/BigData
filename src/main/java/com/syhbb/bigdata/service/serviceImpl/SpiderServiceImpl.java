package com.syhbb.bigdata.service.serviceImpl;

import com.syhbb.bigdata.dataObject.AuthorDO;
import com.syhbb.bigdata.dataObject.CommentDO;
import com.syhbb.bigdata.dataObject.StateDO;
import com.syhbb.bigdata.dataObject.VideoDO;
import com.syhbb.bigdata.service.*;
import com.syhbb.bigdata.spiderData.DO.comment.CommentData;
import com.syhbb.bigdata.spiderData.DO.video.VideoData;
import com.syhbb.bigdata.util.CommentDataDAO;
import com.syhbb.bigdata.util.VideoDataDAO;
import com.syhbb.bigdata.util.Spider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Service
public class SpiderServiceImpl implements SpiderService {
    @Autowired
    private Spider spider;

    @Autowired
    private VideoDataDAO videoDataDAO;

    @Autowired
    private CommentDataDAO commentDataDAO;

    @Autowired
    private MongoVideoService mongoVideoService;

    @Autowired
    private MongoAuthorService mongoAuthorService;

    @Autowired
    private MongoStateService mongoStateService;

    @Autowired
    private MongoCommentService mongoCommentService;

    private static final Logger LOG = LoggerFactory.getLogger(SpiderServiceImpl.class);

    @Override
    public boolean setPopularInMongo(int pageNumber, int pageSize) {
        //请求数据
        List<VideoData> videoData = spider.popularSpider(pageNumber, pageSize);
        //写入原始数据
        List<VideoData> result = videoDataDAO.insertEach(videoData);
        if (result.isEmpty()) {
            LOG.warn("Video原始数据写入失败");
            return false;
        }
        LOG.info("Video数据原始写入成功:" + result.size());
        //数据清洗
        result.forEach(r -> {
            //使用并发容器
            //TODO 收集CompletableFuture
            CompletableFuture
                    .supplyAsync(() -> {
                        //创建DO
                        Map<String, Object> DOMap = new HashMap<>();
                        DOMap.put("VideoDO", new VideoDO(r));
                        DOMap.put("AuthorDO", new AuthorDO(r.getOwner()));
                        DOMap.put("StateDO", new StateDO(r.getStat()));
                        return DOMap;
                    })
                    .thenApply(m -> {
                        //写入MongoDB
                        mongoVideoService.putIntoMongo((VideoDO) m.get("VideoDO"));
                        mongoAuthorService.putIntoMongo((AuthorDO) m.get("AuthorDO"));
                        mongoStateService.putIntoMongo((StateDO) m.get("StateDO"));
                        return ((VideoDO) m.get("VideoDO")).getAvid();
                    })
                    .thenAccept(a -> {
                        setCommentInMongo(a, 1, 1, 20);
                    });

        });
        return true;
    }

    @Override
    public boolean setCommentInMongo(long avid, int sort, int pageNumber, int pageSize) {
        List<CommentData> list = spider.commentSpider(avid, sort, pageNumber, pageSize);
        List<CommentData> result = commentDataDAO.insertEach(list);
        if (result.isEmpty()) {
            LOG.warn("Comment原始数据写入失败");
            return false;
        }
        LOG.info("Comment数据原始写入成功:" + result.size());
        result.forEach(r -> {
            //TODO 收集CompletableFuture
            CompletableFuture
                    .supplyAsync(() -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("CommentDO", new CommentDO(r));
                        map.put("AuthorDO", new AuthorDO(r.getMember()));
                        return map;
                    }).thenAccept(m -> {
                        mongoCommentService.putIntoMongo((CommentDO) m.get("CommentDO"));
                        mongoAuthorService.putIntoMongo((AuthorDO) m.get("AuthorDO"));
                    });
        });
        return true;
    }
    @Override
    public String getDataSourceSize(int pageNumber, int pageSize) {
        StringBuffer sb = new StringBuffer();
        List<VideoData> list = spider.popularSpider(pageNumber, pageSize);
        sb.append("--").append(list.size()).append(":\n");
        list.forEach(l -> {
            sb.append(spider.commentSpider(l.getAid(), 1, 1, 20).size()).append("\n");
        });
        return sb.toString();
    }
}
