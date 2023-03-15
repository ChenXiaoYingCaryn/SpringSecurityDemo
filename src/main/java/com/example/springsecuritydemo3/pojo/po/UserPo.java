package com.example.springsecuritydemo3.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPo {
    private Long userId;
    private String userName;
    private String password;
    private String countryCode;
    private String phone;
    private Long roleId;

    private String roleName;

    public UserPo(String userName, String password, String countryCode, String phone, Long roleId) {
        this.userName = userName;
        this.password = password;
        this.countryCode = countryCode;
        this.phone = phone;
        this.roleId = roleId;
    }

}
