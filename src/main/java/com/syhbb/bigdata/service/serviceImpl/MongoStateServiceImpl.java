package com.syhbb.bigdata.service.serviceImpl;

import com.syhbb.bigdata.dao.StateDAO;
import com.syhbb.bigdata.dataObject.StateDO;
import com.syhbb.bigdata.service.MongoStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoStateServiceImpl implements MongoStateService {

    @Autowired
    private StateDAO stateDAO;

    private static final Logger LOG = LoggerFactory.getLogger(MongoStateServiceImpl.class);

    @Override
    public void putIntoMongo(StateDO stateDO) {
        long avid = stateDO.getAvid();
        if (stateDAO.getByAvid(avid).isEmpty()) {
            stateDAO.addDO(stateDO);
            LOG.info("State写入成功:" + avid);
        } else {
            if (stateDAO.modifyByAvid(stateDO)) {
                LOG.info("State更新成功:" + avid);
            } else {
                LOG.warn("State更新失败:" + avid);
            }
        }
    }
}
