package com.syhbb.bigdata.util.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.syhbb.bigdata.spiderData.DO.comment.CommentData;
import com.syhbb.bigdata.spiderData.DO.comment.CommentResponseData;
import com.syhbb.bigdata.spiderData.DO.video.VideoData;
import com.syhbb.bigdata.spiderData.DO.video.VideoResponseData;
import com.syhbb.bigdata.spiderData.ResponseData;
import com.syhbb.bigdata.util.Spider;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SpiderImpl implements Spider {
    @Value("${spider.url.popular}")
    private String popularURL;

    @Value("${spider.url.comment}")
    private String commentURL;

    private static final Logger LOG  = LoggerFactory.getLogger(SpiderImpl.class);
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(5,TimeUnit.MINUTES)
            .build();

    private String doSpider(Request request) {
        String jsonString = null;
        try {
            Response response = this.okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                jsonString = response.body().string();
                LOG.info("请求成功");
            } else {
                LOG.error("请求异常");
            }
        } catch (IOException e) {
            LOG.error("请求异常");
            LOG.error(e.getMessage());
        }
        return jsonString;
    }

    //TODO parse复用，使用interface 和 TypeReference


    private List<VideoData> parseVideoDate(String jsonString){
        ResponseData<VideoResponseData> responseData = new ResponseData<>();
        List<VideoData> videoDataList = new ArrayList<VideoData>();
        try {
            responseData = JSON.parseObject(jsonString, new TypeReference<ResponseData<VideoResponseData>>() {
//                @Override
//                public ResponseData<VideoResponseData> parseObject(String text) {
//                    return super.parseObject(text);
//                }
            });
            videoDataList = responseData.getData().getList();
            LOG.info("JSON反序列化成功成功");
        } catch (Exception e) {
            LOG.warn("JSON反序列化异常");
            LOG.warn(e.getMessage());
        }
        return videoDataList;
    }


    private List<CommentData> parseCommentDate(String jsonString) {
        ResponseData<CommentResponseData> responseData = new ResponseData<>();
        List<CommentData> commentDataList;
        try {
            responseData = JSON.parseObject(jsonString, new TypeReference<ResponseData<CommentResponseData>>() {
                @Override
                public ResponseData<CommentResponseData> parseObject(String text) {
                    return super.parseObject(text);
                }
            });
            LOG.info("JSON反序列化成功成功");
        } catch (Exception e) {
            LOG.warn("JSON反序列化异常");
            LOG.warn(e.getMessage());
        }
        if (responseData.getCode() != 0) {
            LOG.error(responseData.getCode() + responseData.getMessage());
            return new ArrayList<>();
        }
        commentDataList = responseData.getData().getReplies();
        return commentDataList;
    }
    @Override
    public List<VideoData> popularSpider(int pageNumber,int pageSize){
        Request request = new Request.Builder()
                .url(popularURL + "?pn=" + pageNumber + "&ps=" + pageSize)
                .addHeader("user-agent", "Mozilla/5.0")
                .build();
        return parseVideoDate(doSpider(request));
    }

    @Override
    public List<CommentData> commentSpider(long avid, int sort, int pageNumber, int pageSize) {
        Request request = new Request.Builder()
                .url(commentURL + "?type=1&oid=" + avid +"&sort=" + sort + "&pn=" + pageNumber + "&ps=" + pageSize)
                .build();
        return parseCommentDate(doSpider(request));
    }
}
