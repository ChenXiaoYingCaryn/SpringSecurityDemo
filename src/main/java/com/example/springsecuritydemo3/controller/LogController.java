package com.example.springsecuritydemo3.controller;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.pojo.vo.LogVo;
import com.example.springsecuritydemo3.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/queryUserOperationLogByUsername")
    public CommonResult<List<LogVo>> queryUserOperationLogByUsername(String username){
        List<LogVo> res = logService.queryUserOperationLogByUsername(username);
        return CommonResult.success(res);
    }

    @GetMapping("/queryUserOperationLog")
    public CommonResult<List<LogVo>> queryUserOperationLog(){
        List<LogVo> res = logService.queryUserOperationLog();
        return CommonResult.success(res);
    }

}
