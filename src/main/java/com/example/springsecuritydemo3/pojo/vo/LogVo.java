package com.example.springsecuritydemo3.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogVo {
    private String id;
    private String userName;
    private String operation;
    private String createTime;
}
