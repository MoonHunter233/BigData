package com.syhbb.bigdata.model;

import org.apache.spark.sql.Row;

public class Word {
    private String content;
    private long count;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Word() {
    }

    public Word(String content, long count) {
        this.content = content;
        this.count = count;
    }

    public Word(Row row){
        this.content = row.getString(0);
        this.count = row.getLong(1);
    }
}
