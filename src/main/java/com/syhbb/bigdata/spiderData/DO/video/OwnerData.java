package com.syhbb.bigdata.spiderData.DO.video;

public class OwnerData {
    private long mid;       //用户id
    private String name;    //用户名
    private String face;    //头像url

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }
}
