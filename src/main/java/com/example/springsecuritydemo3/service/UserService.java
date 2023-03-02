package com.example.springsecuritydemo3.service;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.pojo.dto.UserDto;
import com.example.springsecuritydemo3.pojo.dto.UserRegisterDto;

public interface UserService {

    /**
     * 根据用户名查询手机号
     * @param username
     * @return
     */
    CommonResult findPhoneByUsername(String username);

    CommonResult getPassword(String password);

    /**
     * 用户登出
     * @param username
     * @return
     */
    CommonResult logout(String username);

    /**
     * 登录
     * @param userDto
     * @return
     */
    CommonResult login(UserDto userDto);

    /**
     * 用户注册
     * @param userRegisterDto
     * @return
     */
    CommonResult register(UserRegisterDto userRegisterDto);
}
