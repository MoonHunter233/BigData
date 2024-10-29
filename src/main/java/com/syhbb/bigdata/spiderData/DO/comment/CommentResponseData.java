package com.syhbb.bigdata.spiderData.DO.comment;

import com.syhbb.bigdata.spiderData.DO.video.VideoResponseData;

import java.util.List;

public class CommentResponseData extends VideoResponseData {
    private List<CommentData> replies;

    public List<CommentData> getReplies() {
        return replies;
    }
    public void setReplies(List<CommentData> replies) {
        this.replies = replies;
    }
}
