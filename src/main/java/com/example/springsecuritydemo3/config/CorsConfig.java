package com.example.springsecuritydemo3.config;

import com.example.corsplugindemo.service.CorsService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author xiaoying
 * @create 2023/4/14 22:47
 */

@Configuration
public class CorsConfig {

    @Resource
    private CorsService corsService;

    @Bean
    public FilterRegistrationBean createCorsFilter(){
        return corsService.corsFilter();
    }

}
