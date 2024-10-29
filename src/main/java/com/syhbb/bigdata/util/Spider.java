package com.syhbb.bigdata.util;

import com.syhbb.bigdata.spiderData.DO.comment.CommentData;
import com.syhbb.bigdata.spiderData.DO.video.VideoData;
import okhttp3.Request;

import java.util.List;

public interface Spider {
        public List<VideoData> popularSpider(int pageNumber,int pageSize);
        public List<CommentData> commentSpider(long avid, int sort, int pageNumber,int pageSize);
}
