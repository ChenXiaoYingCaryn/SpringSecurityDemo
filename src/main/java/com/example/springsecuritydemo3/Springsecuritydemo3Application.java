package com.example.springsecuritydemo3;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "SpringSecurityDemo.application.properties", autoRefreshed = true)
public class Springsecuritydemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(Springsecuritydemo3Application.class, args);
    }

}
