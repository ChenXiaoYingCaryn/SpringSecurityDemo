package com.example.springsecuritydemo3.controller;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/root")
public class RootController {

    @Autowired
    private UserService userService;

    @PostMapping("/forcedToLogOut")
    public CommonResult<String> forcedToLogOut(String username){
        String token = userService.logout(username);
        return CommonResult.success(token);
    }
}
