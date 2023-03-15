package com.example.springsecuritydemo3.controller;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.pojo.dto.UserDto;
import com.example.springsecuritydemo3.pojo.dto.UserRegisterDto;
import com.example.springsecuritydemo3.pojo.vo.PhoneVo;
import com.example.springsecuritydemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/findPhoneByUsername")
    public CommonResult<PhoneVo> findPhoneByUsername (String username){
        PhoneVo vo;
        try {
            vo = userService.findPhoneByUsername(username);
        }catch (Exception e){
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(vo);
    }

    @PostMapping("/logout")
    public  CommonResult<String> logout(String username){
        String token;
        try {
            token = userService.logout(username);
        }catch (Exception e){
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(token);
    }

    @PostMapping("/login")
    public CommonResult<String> login(UserDto userDto){
        String token;
        try {
            token = userService.login(userDto);
        }catch (Exception e){
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(token);
    }

    @PostMapping("/register")
    public CommonResult<String> register(UserRegisterDto userRegisterDto){
        try {
            userService.register(userRegisterDto);
        }catch (Exception e){
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success("注册成功");
    }

}
