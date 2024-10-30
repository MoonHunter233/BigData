package com.syhbb.bigdata.api;

import com.kennycason.kumo.WordFrequency;
import com.syhbb.bigdata.dao.VideoDAO;
import com.syhbb.bigdata.dataObject.VideoDO;
import com.syhbb.bigdata.service.DBService;
import com.syhbb.bigdata.service.MongoVideoService;
import com.syhbb.bigdata.service.SpiderService;
import com.syhbb.bigdata.service.ChartService;
import com.syhbb.bigdata.util.MongoDBSpark;
import com.syhbb.bigdata.util.Spider;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/MongoDBAndSpark/API")
public class MongoDBApi {
    @Autowired
    private Spider spider;

    @Autowired
    private SpiderService spiderService;

    @Autowired
    private DBService dbService;

    @Autowired
    private VideoDAO videoDAO;

    @Autowired
    private MongoVideoService mongoVideoService;

    @Autowired
    private MongoDBSpark mongoDBSpark;

    @Autowired
    private ChartService chartService;

    private static final Logger LOG = LoggerFactory.getLogger(MongoDBApi.class);

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @GetMapping("/popularInto0")
    @ResponseBody
    public String popularInto(@RequestParam(name = "pn", defaultValue = "1") int pageNumber, @RequestParam(name = "ps", defaultValue = "1") int pageSize) {
        spiderService.setPopularInMongo(pageNumber, pageSize);
        LOG.info("写入成功");
        return "OK";
    }

    @GetMapping("/popularInto")
    @ResponseBody
    public String popularInto2() {
        if (mongoVideoService.isUpdate()) {
            LOG.info("今日已更新");
            return "今日已更新 " + dateTimeFormatter.format(LocalDateTime.now());
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int pageSize = 50;
        for (int i = 1; i < 11; i++) {
            var pageNumber = i;
            Runnable runnable = () -> spiderService.setPopularInMongo(pageNumber, pageSize);
            executorService.execute(runnable);
        }
        executorService.shutdown();
        return "今日更新完成 " + dateTimeFormatter.format(LocalDateTime.now());
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam("avid") long avid) {
        return videoDAO.getDObyAvid(avid).getTitle();
    }

    @GetMapping("/isUpdate")
    @ResponseBody
    public String isUpdate() {
        return mongoVideoService.isUpdate() ? "true" : "false";
    }


    @GetMapping("/getCommentSize")
    @ResponseBody
    public String getCommentSize() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < 11; i++) {
            var pageNumber = i;
            Runnable runnable = () -> sb.append(spiderService.getDataSourceSize(pageNumber, 50)).append("<br>");
            executorService.execute(runnable);
        }
        executorService.shutdown();
        return sb.toString();
    }

    @GetMapping("/updateComment")
    @ResponseBody
    public String upd() {
        dbService.updateToDayComment();
        return "更新完成 " + dateTimeFormatter.format(LocalDateTime.now());
    }

    @GetMapping("/count")
    @ResponseBody
    public long getCount() {
        mongoDBSpark.init();
        return mongoDBSpark.dataCont();
    }

    @GetMapping("/wordCloud")
    @ResponseBody
    public byte[] wordCloud() {
        List<WordFrequency> frequencies = mongoDBSpark.getCommentWordFrequency();
        File file = chartService.createWordCloud(frequencies);
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOG.error("文件转换失败");
            throw new RuntimeException(e);
        }
        return imageBytes;
    }

    @GetMapping("/pieChart")
    @ResponseBody
    public byte[] pieChart() {
        DefaultPieDataset pieDataset = mongoDBSpark.getPieDataset();
        File file = chartService.creatPieChart(pieDataset);
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOG.error("文件转换失败");
            throw new RuntimeException(e);
        }
        return imageBytes;
    }

    @GetMapping("/barChart")
    @ResponseBody
    public byte[] barChart() {
        DefaultCategoryDataset categoryDataset = mongoDBSpark.getCategoryDataset();
        File file = chartService.creatBarChart(categoryDataset);
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOG.error("文件转换失败");
            throw new RuntimeException(e);
        }
        return imageBytes;
    }

    @GetMapping("/todayVideo")
    @ResponseBody
    public List<VideoDO> todayVideo() {
        return videoDAO.getDObyToday();
    }

    @GetMapping("/allVideo")
    @ResponseBody
    public List<VideoDO> allVideo() {
        LOG.info("get all video");
        return videoDAO.findAll();
    }

    @GetMapping("/getVideo")
    @ResponseBody
    public List<VideoDO> getVideo(@Param("pageNum") int pageNum) {
        LOG.info("get video");
        return videoDAO.getDObyPage(pageNum);
    }
}
