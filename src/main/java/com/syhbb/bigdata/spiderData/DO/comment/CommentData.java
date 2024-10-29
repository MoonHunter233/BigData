package com.syhbb.bigdata.spiderData.DO.comment;

public class CommentData {
    private long rpid;
    private long oid;
    private int type;
    private long mid;
    private int like;
    private MemberDO member;
    private ContentData content;

    public long getRpid() {
        return rpid;
    }

    public void setRpid(long rpid) {
        this.rpid = rpid;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public MemberDO getMember() {
        return member;
    }

    public void setMember(MemberDO member) {
        this.member = member;
    }

    public ContentData getContent() {
        return content;
    }

    public void setContent(ContentData content) {
        this.content = content;
    }
}
