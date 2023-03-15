package com.example.springsecuritydemo3.controller;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/queryUserOperationLogByUsername")
    public CommonResult<String> queryUserOperationLogByUsername(String username){
        return logService.queryUserOperationLogByUsername(username);
    }

    @GetMapping("/queryUserOperationLog")
    public CommonResult<String> queryUserOperationLog(){
        return logService.queryUserOperationLog();
    }

}
