package com.example.springsecuritydemo3.service;

import com.example.springsecuritydemo3.pojo.po.LogPo;
import com.example.springsecuritydemo3.pojo.vo.LogVo;

import java.util.List;


public interface LogService {

    List<LogVo> queryUserOperationLogByUsername(String username);

    List<LogVo> queryUserOperationLog();

    void save(LogPo logPo);
}
