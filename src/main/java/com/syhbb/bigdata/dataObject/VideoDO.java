package com.syhbb.bigdata.dataObject;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.syhbb.bigdata.spiderData.DO.video.VideoData;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class VideoDO implements Serializable {
    private long avid;
    private String themeName;
    private String coverURL;
    private String title;
    private LocalDateTime publishTime;
    private String description;
    private int duration;
    private String authorId;
    private String dynamic;
    private String shortLink;
    private String publishLocation;
    private String RecommendReason;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate date;

    public long getAvid() {
        return avid;
    }

    public void setAvid(long avid) {
        this.avid = avid;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public String getPublishLocation() {
        return publishLocation;
    }

    public void setPublishLocation(String publishLocation) {
        this.publishLocation = publishLocation;
    }

    public String getRecommendReason() {
        return RecommendReason;
    }

    public void setRecommendReason(String recommendReason) {
        RecommendReason = recommendReason;
    }


    public VideoDO(VideoData videoDate) {
        avid = videoDate.getAid();
        themeName = videoDate.getTname();
        coverURL = videoDate.getPic();
        title = videoDate.getTitle();
        Instant instant = Instant.ofEpochSecond(videoDate.getPubdate());
        publishTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        description = videoDate.getDesc();
        duration = videoDate.getDuration();
        authorId = videoDate.getOwner().getMid()+"";
        dynamic = videoDate.getDynamic();
        shortLink = videoDate.getShort_link_v2();
        publishLocation = videoDate.getPub_location();
        RecommendReason = videoDate.getRcmd_reason().getContent();
        date = LocalDate.now();
    }

    public VideoDO() {
    }
}
