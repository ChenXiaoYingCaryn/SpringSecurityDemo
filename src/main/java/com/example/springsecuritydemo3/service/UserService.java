package com.example.springsecuritydemo3.service;

import com.example.springsecuritydemo3.pojo.dto.UserDto;
import com.example.springsecuritydemo3.pojo.dto.UserRegisterDto;

public interface UserService {

    /**
     * 根据用户名查询手机号
     * @param username
     * @return
     */
    String findPhoneByUsername(java.lang.String username);

    /**
     * 用户登出
     * @param username
     * @return
     */
    String logout(java.lang.String username);

    /**
     * 登录
     * @param userDto
     * @return
     */
    String login(UserDto userDto);

    /**
     * 用户注册
     * @param userRegisterDto
     */
    void register(UserRegisterDto userRegisterDto);
}
