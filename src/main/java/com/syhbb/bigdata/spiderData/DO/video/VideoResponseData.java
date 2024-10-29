package com.syhbb.bigdata.spiderData.DO.video;

import com.syhbb.bigdata.spiderData.ResponseData;

import java.util.ArrayList;
import java.util.List;

public class VideoResponseData extends ResponseData {
    private List<VideoData> list;
    private boolean no_more;

    public List<VideoData> getList() {
        return list;
    }

    public void setList(List<VideoData> list) {
        this.list = list;
    }

    public boolean isNo_more() {
        return no_more;
    }

    public void setNo_more(boolean no_more) {
        this.no_more = no_more;
    }

    public VideoResponseData() {
        list = new ArrayList<>();
    }
}