package com.example.springsecuritydemo3.controller;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.pojo.dto.UserDto;
import com.example.springsecuritydemo3.pojo.dto.UserRegisterDto;
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
    public CommonResult<String> findPhoneByUsername (String username){
        return userService.findPhoneByUsername(username);
    }

    @PostMapping("/logout")
    public  CommonResult<String> logout(String username){
        return userService.logout(username);
    }

    @PostMapping("/login")
    public CommonResult<UserDto> login(UserDto userDto){
        return userService.login(userDto);
    }

    @PostMapping("/register")
    public CommonResult<UserRegisterDto> register(UserRegisterDto userRegisterDto){
        return userService.register(userRegisterDto);
    }

}
