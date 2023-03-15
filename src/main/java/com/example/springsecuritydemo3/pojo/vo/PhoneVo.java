package com.example.springsecuritydemo3.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xiaoying
 * @create 2023/3/12
 */
@Data
@AllArgsConstructor
public class PhoneVo {
    private String countryCode;
    private String countryName;
    private String phone;
}
