package com.syhbb.bigdata.service.serviceImpl;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.PixelBoundaryBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.palette.ColorPalette;
import com.syhbb.bigdata.service.ChartService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {
    @Qualifier("webApplicationContext")
    @Autowired
    private ResourceLoader loader;


    private static final Logger LOG = LoggerFactory.getLogger(ChartService.class);


    @Override
    public File createWordCloud(List<WordFrequency> frequencies) {
        Dimension dimension = new Dimension(1200, 600);
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
//        try {
//            wordCloud.setBackground(new PixelBoundaryBackground("backgrounds/whale_small.png"));
//        } catch (IOException e) {
//            LOG.error(e.getMessage());
//        }
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setBackgroundColor(new Color(0,0,0,0));
        wordCloud.setColorPalette(new ColorPalette(new Color(0xD5CFFA), new Color(0xBBB1FA), new Color(0x9A8CF5), new Color(0x806EF5)));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 45));
        wordCloud.build(frequencies);
        File file = new File("src/main/resources/WordCloud/comment_word_cloud_" + Instant.now().toEpochMilli() + ".png");
        try {
            wordCloud.writeToFile(file.getPath());
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        LOG.info("生成词云完成");
        return file;
    }

    @Override
    public File creatPieChart(DefaultPieDataset pieDataset) {
        JFreeChart pieChart =
                ChartFactory.createPieChart("video published location", pieDataset, true, true, false);
        File file = new File("src/main/resources/PieChart/location_pie_chart" + Instant.now().toEpochMilli() + ".png");
        try {
            ChartUtils.saveChartAsPNG(file, pieChart, 3000, 1500);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        LOG.info("生成饼图完成");
        return file;
    }

    @Override
    public File creatBarChart(DefaultCategoryDataset categoryDataset) {
        JFreeChart barChart =
                ChartFactory.createBarChart("video published location",
                        "location",
                        "count",
                        categoryDataset,
                        PlotOrientation.VERTICAL,
                        true, true, false
                );
        File file = new File("src/main/resources/BarChart/location_Bar_chart" + Instant.now().toEpochMilli() + ".png");
        try {
            ChartUtils.saveChartAsPNG(file, barChart, 3000, 1500);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        LOG.info("生成柱状图完成");
        return file;
    }
}
