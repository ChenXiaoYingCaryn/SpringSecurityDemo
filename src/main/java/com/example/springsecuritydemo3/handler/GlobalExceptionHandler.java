package com.example.springsecuritydemo3.handler;

import com.example.springsecuritydemo3.common.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xiaoying
 * @create 9/4/2023 1:29 pm
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        return CommonResult.failed(e.getMessage());
    }
}
