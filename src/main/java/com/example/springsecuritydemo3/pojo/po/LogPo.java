package com.example.springsecuritydemo3.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogPo {
    private String userName;
    private String operation;
    private String createTime;
}
