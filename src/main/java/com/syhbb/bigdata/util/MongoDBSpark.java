package com.syhbb.bigdata.util;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.kennycason.kumo.WordFrequency;
import com.syhbb.bigdata.dao.CommentDAO;
import com.syhbb.bigdata.dao.VideoDAO;
import com.syhbb.bigdata.dataObject.CommentDO;

import com.syhbb.bigdata.dataObject.VideoDO;
import com.syhbb.bigdata.model.Word;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.desc;


@Component
public class MongoDBSpark {
    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private VideoDAO videoDAO;

    private static final Logger LOG = LoggerFactory.getLogger(MongoDBSpark.class);

    private SparkSession spark;
    private Dataset<CommentDO> commentsDataset;
    private Dataset<VideoDO> videosDataset;


    public void initialization() {
        commentsDataset = spark.createDataset(commentDAO.findAll(), Encoders.kryo(CommentDO.class));
        videosDataset = spark.createDataset(videoDAO.getDObyToday(), Encoders.kryo(VideoDO.class));
    }

    @PostConstruct
    public void init() {
        spark = SparkSession.builder()
                .appName("spark")
                .master("local")
                .getOrCreate();
        initialization();
    }

    public long dataCont() {
        Dataset<String> words = commentsDataset.flatMap((FlatMapFunction<CommentDO, String>) d -> {
            JiebaSegmenter segmenter = new JiebaSegmenter();
            List<String> tokens = segmenter.sentenceProcess(d.getContent());
            return tokens.iterator();
        }, Encoders.STRING());
        Dataset<Row> df = words
                .filter((FilterFunction<String>) d -> d.length() > 2)
                .groupBy("value")
                .count()
                .withColumnRenamed("value", "value")
                .withColumnRenamed("count", "count")
                .orderBy(desc("count"))
                .limit(200);
        List<Row> rows = df.collectAsList();
        List<Word> wordList = rows.stream().map(Word::new).collect(Collectors.toList());
        return df.count();
    }

    public List<WordFrequency> getCommentWordFrequency() {
        Dataset<String> words = commentsDataset.flatMap((FlatMapFunction<CommentDO, String>) d -> {
            JiebaSegmenter segmenter = new JiebaSegmenter();
            List<String> tokens = segmenter.sentenceProcess(d.getContent());
            return tokens.iterator();
        }, Encoders.STRING());
        Dataset<Row> df = words
                .filter((FilterFunction<String>) d -> d.length() > 2)
                .groupBy("value")
                .count()
                .withColumnRenamed("value", "value")
                .withColumnRenamed("count", "count")
                .orderBy(desc("count"))
                .limit(300);
        List<Row> rows = df.collectAsList();
        List<WordFrequency> frequencies = new ArrayList<>();
//        try {
//            frequencies = rows.stream()
//                    .map(d -> new WordFrequency(d.getString(0), (int) d.getLong(1)))
//                    .collect(Collectors.toList());
//        } catch (Exception e) {

//        }
        rows.forEach(d -> frequencies.add(new WordFrequency(d.getString(0), (int) d.getLong(1))));
        LOG.info("Spark操作完成");
        return frequencies;
    }

    public DefaultPieDataset getPieDataset() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        List<Row> rows = toListRow();
        rows.forEach(d -> pieDataset.setValue(d.getString(0), d.getLong(1)));
        return pieDataset;
    }

    public DefaultCategoryDataset getCategoryDataset() {
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        List<Row> rows = toListRow();
        rows.forEach(d -> categoryDataset.addValue(d.getLong(1), "Sales", d.getString(0)));
        return categoryDataset;
    }
    private List<Row> toListRow () {
        Dataset<String> location = videosDataset.map((MapFunction<VideoDO, String>) VideoDO::getPublishLocation, Encoders.STRING());
        Dataset<Row> df = location
                .filter((FilterFunction<String>) Objects::nonNull)
                .groupBy("value")
                .count()
                .withColumnRenamed("value", "value")
                .withColumnRenamed("count", "count")
                .orderBy(desc("count"));
        LOG.info("Spark操作完成");
        return df.collectAsList();
    }
}
