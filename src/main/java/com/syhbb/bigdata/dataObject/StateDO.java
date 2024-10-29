package com.syhbb.bigdata.dataObject;

import com.syhbb.bigdata.spiderData.DO.video.StatData;

public class StateDO {
    private long avid;
    private int view;       //观看次数
    private int danMu;    //弹幕数
    private int comment;     //评论数
    private int favorite;   //收藏数
    private int coin;       //投币数
    private int share;      //分享数
    private int nowRank;   //当前排名
    private int hisRank;   //历史排名
    private int like;       //点赞数
    private int dislike;

    public long getAvid() {
        return avid;
    }

    public void setAvid(long avid) {
        this.avid = avid;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getDanMu() {
        return danMu;
    }

    public void setDanMu(int danMu) {
        this.danMu = danMu;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getNowRank() {
        return nowRank;
    }

    public void setNowRank(int nowRank) {
        this.nowRank = nowRank;
    }

    public int getHisRank() {
        return hisRank;
    }

    public void setHisRank(int hisRank) {
        this.hisRank = hisRank;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public StateDO(StatData statData) {
        avid = statData.getAid();
        view = statData.getView();
        danMu = statData.getDanmaku();
        comment = statData.getReplay();
        favorite = statData.getFavorite();
        coin = statData.getCoin();
        share = statData.getShare();
        nowRank = statData.getNow_rank();
        hisRank = statData.getHis_rank();
        like = statData.getLike();
        dislike = statData.getDislike();
    }

    public StateDO() {
    }
}
