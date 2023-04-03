package com.example.springsecuritydemo3.service;

import com.example.springsecuritydemo3.pojo.dto.UserDto;
import com.example.springsecuritydemo3.pojo.dto.UserRegisterDto;
import com.example.springsecuritydemo3.pojo.vo.PhoneVo;
import com.example.springsecuritydemo3.pojo.vo.UserMsgVo;

public interface UserService {

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    UserMsgVo getUserMsg(String username);

    /**
     * 根据用户名查询手机号
     * @param username
     * @return
     */
    PhoneVo findPhoneByUsername(String username);

    /**
     * 用户登出
     * @param username
     * @return
     */
    String logout(String username);

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
