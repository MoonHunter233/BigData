package com.syhbb.bigdata.service.serviceImpl;

import com.syhbb.bigdata.dao.AuthorDAO;
import com.syhbb.bigdata.dataObject.AuthorDO;
import com.syhbb.bigdata.service.MongoAuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoAuthorServiceImpl implements MongoAuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    private static final Logger LOG = LoggerFactory.getLogger(MongoAuthorServiceImpl.class);

    @Override
    public void putIntoMongo(AuthorDO authorDO) {
        long id = authorDO.getId();
        if (authorDAO.getById(id).isEmpty()) {
            authorDAO.addDO(authorDO);
            LOG.info("Author写入成功:" + id);
        } else {
            if (authorDAO.modifyById(authorDO)) {
                LOG.info("Author更新成功:" + id);
            } else {
                LOG.warn("Author更新失败:" + id);
            }
        }
    }
}
