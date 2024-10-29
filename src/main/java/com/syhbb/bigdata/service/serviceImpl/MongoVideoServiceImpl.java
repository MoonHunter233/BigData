package com.syhbb.bigdata.service.serviceImpl;

import com.syhbb.bigdata.dao.VideoDAO;
import com.syhbb.bigdata.dataObject.VideoDO;
import com.syhbb.bigdata.service.MongoVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MongoVideoServiceImpl implements MongoVideoService {

    @Autowired
    private VideoDAO videoDAO;

    private static final Logger LOG = LoggerFactory.getLogger(MongoVideoServiceImpl.class);

    @Override
    public void putIntoMongo(VideoDO videoDO) {
        videoDAO.addDO(videoDO);
        LOG.info("Video写入成功:" + videoDO.getAvid());
    }

    @Override
    public boolean isUpdate() {
        return !videoDAO.getDObyToday().isEmpty();
    }
}
