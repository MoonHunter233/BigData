package com.syhbb.bigdata.service.serviceImpl;

import com.syhbb.bigdata.dao.CommentDAO;
import com.syhbb.bigdata.dataObject.CommentDO;
import com.syhbb.bigdata.service.MongoCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoCommentServiceImpl implements MongoCommentService {
    @Autowired
    private CommentDAO commentDAO;

    private static final Logger LOG = LoggerFactory.getLogger(MongoVideoServiceImpl.class);

    @Override
    public void putIntoMongo(CommentDO commentDO) {
        long commentId = commentDO.getCommentId();
        if (commentDAO.findByCommentId(commentId).isEmpty()){
            commentDAO.addDO(commentDO);
            LOG.info("Comment写入成功:" + commentId);
        } else {
            if (commentDAO.modifyByCommentId(commentDO)) {
                LOG.info("Comment更新成功:" + commentId);
            } else {
                LOG.warn("Comment更新失败:" + commentId);
            }
        }
    }
}
