package com.example.springsecuritydemo3.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @author xiaoying
 * @create 2023/3/21 23:01
 */
@Data
@AllArgsConstructor
public class UserMsgVo {
    private Long id;
    private String username;
    private String countryCode;
    private String phone;
    private Set<RoleVo> roles;
}
