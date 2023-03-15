package com.example.springsecuritydemo3.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterDto {
    String userName;
    String password;
    String countryCode;
    String phone;
}
