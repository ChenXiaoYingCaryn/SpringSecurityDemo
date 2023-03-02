package com.example.springsecuritydemo3.service;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.pojo.po.LogPo;


public interface LogService {

    CommonResult queryUserOperationLogByUsername(String username);

    CommonResult queryUserOperationLog();

    void save(LogPo logPo);
}
