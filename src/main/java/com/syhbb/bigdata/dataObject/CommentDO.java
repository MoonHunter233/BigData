package com.syhbb.bigdata.dataObject;

import com.syhbb.bigdata.spiderData.DO.comment.CommentData;

public class CommentDO {
    private long commentId;
    private long avid;
    private long authorId;
    private int like;
    private String content;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getAvid() {
        return avid;
    }

    public void setAvid(long avid) {
        this.avid = avid;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentDO() {
    }

    public CommentDO(CommentData commentData) {
        commentId = commentData.getRpid();
        avid = commentData.getOid();
        authorId = commentData.getMid();
        like = commentData.getLike();
        content = commentData.getContent().getMessage();
    }
}
