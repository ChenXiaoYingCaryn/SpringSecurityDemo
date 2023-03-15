package com.example.springsecuritydemo3.service.impl;

import com.example.springsecuritydemo3.dao.LogDao;
import com.example.springsecuritydemo3.pojo.po.LogPo;
import com.example.springsecuritydemo3.pojo.vo.LogVo;
import com.example.springsecuritydemo3.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    LogDao logDao;

    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Override
    public List<LogVo> queryUserOperationLogByUsername(String username) {
        List<LogVo> list;
        try {
            list = logDao.queryUserOperationLogByUsername(username);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    @Override
    public List<LogVo> queryUserOperationLog() {
        List<LogVo> list;
        try {
            list = logDao.queryUserOperationLog();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    @Override
    public void save(LogPo logPo) {
        try{
            logDao.save(logPo);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
