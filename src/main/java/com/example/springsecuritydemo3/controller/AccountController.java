package com.example.springsecuritydemo3.controller;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.component.JwtTokenProvider;
import com.example.springsecuritydemo3.pojo.dto.UserDto;
import com.example.springsecuritydemo3.pojo.dto.UserRegisterDto;
import com.example.springsecuritydemo3.pojo.vo.LogoutVo;
import com.example.springsecuritydemo3.pojo.vo.UserMsgVo;
import com.example.springsecuritydemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaoying
 * @create 2023/3/23 14:33
 */
@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/logout")
    public CommonResult<LogoutVo> logout(String username, HttpServletResponse response){
        String token;
        LogoutVo vo = new LogoutVo();

        token = userService.logout(username);

        Cookie cookie = new Cookie("token", token);
        cookie.setDomain("backend.mihoyo.com"); // 设置可信域
        cookie.setMaxAge(60 * 60 * 24);  // 设置过期时间
        response.addCookie(cookie);

        return CommonResult.success(vo);
    }

    @PostMapping("/login")
    public CommonResult<UserMsgVo> login(UserDto userDto, HttpServletResponse response){
        String token;
        UserMsgVo vo;

        token = userService.login(userDto);
        vo = userService.getUserMsg(userDto.getUsername());

        Cookie cookie = new Cookie("token", token);
        cookie.setDomain("backend.mihoyo.com"); // 设置可信域
        cookie.setMaxAge(60 * 60 * 24);  // 设置过期时间
        response.addCookie(cookie);

        return CommonResult.success(vo);
    }

    @PostMapping("/register")
    public CommonResult<UserMsgVo> register(UserRegisterDto userRegisterDto){
        userService.register(userRegisterDto);
        UserMsgVo vo = userService.getUserMsg(userRegisterDto.getUserName());
        return CommonResult.success(vo);
    }
}
