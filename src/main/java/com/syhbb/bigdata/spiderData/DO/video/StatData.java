package com.syhbb.bigdata.spiderData.DO.video;

public class StatData {

    private long aid;
    private int view;       //观看次数
    private int danmaku;    //弹幕数
    private int replay;     //评论数
    private int favorite;   //收藏数
    private int coin;       //投币数
    private int share;      //分享数
    private int now_rank;   //当前排名
    private int his_rank;   //历史排名
    private int like;       //点赞数
    private int dislike;

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getDanmaku() {
        return danmaku;
    }

    public void setDanmaku(int danmaku) {
        this.danmaku = danmaku;
    }

    public int getReplay() {
        return replay;
    }

    public void setReplay(int replay) {
        this.replay = replay;
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

    public int getNow_rank() {
        return now_rank;
    }

    public void setNow_rank(int now_rank) {
        this.now_rank = now_rank;
    }

    public int getHis_rank() {
        return his_rank;
    }

    public void setHis_rank(int his_rank) {
        this.his_rank = his_rank;
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
}
