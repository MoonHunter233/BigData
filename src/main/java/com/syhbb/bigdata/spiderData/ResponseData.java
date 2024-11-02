package com.syhbb.bigdata.spiderData;


import com.syhbb.bigdata.spiderData.DO.video.VideoData;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> {
    private int code;
    private String message;
    private int ttl;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }

}
