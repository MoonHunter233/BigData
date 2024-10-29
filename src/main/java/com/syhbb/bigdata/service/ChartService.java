package com.syhbb.bigdata.service;

import com.kennycason.kumo.WordFrequency;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ChartService {
    public File createWordCloud(List<WordFrequency> frequencies);

    public File creatPieChart(DefaultPieDataset pieDataset);

    public File creatBarChart(DefaultCategoryDataset categoryDataset);
}
