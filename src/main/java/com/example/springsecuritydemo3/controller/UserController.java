package com.example.springsecuritydemo3.controller;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.pojo.vo.PhoneVo;
import com.example.springsecuritydemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://front.mihuyo.com")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/findPhoneByUsername")
    public CommonResult<PhoneVo> findPhoneByUsername (String username){
        PhoneVo vo = userService.findPhoneByUsername(username);
        return CommonResult.success(vo);
    }


}
