package com.syhbb.bigdata.service.serviceImpl;

import com.syhbb.bigdata.dao.CommentDAO;
import com.syhbb.bigdata.dao.VideoDAO;
import com.syhbb.bigdata.dataObject.VideoDO;
import com.syhbb.bigdata.service.DBService;
import com.syhbb.bigdata.service.SpiderService;
import com.syhbb.bigdata.util.Spider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBServiceImpl implements DBService {
    @Autowired
    private VideoDAO videoDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private SpiderService spiderService;

    private final static Logger LOG = LoggerFactory.getLogger(DBServiceImpl.class);

    @Override
    public void updateToDayComment() {
        List<VideoDO> list = videoDAO.getDObyToday();
        if (list.isEmpty()){
            LOG.warn("今日视屏:0");
            return;
        }
        LOG.info("今日视屏获取:" + list.size());
        list.parallelStream().forEach(l -> spiderService.setCommentInMongo(l.getAvid(), 1, 1, 20));
    }

    @Override
    public void updateAllComment() {

    }
}
