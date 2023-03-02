package com.example.springsecuritydemo3.aspect;

import com.example.springsecuritydemo3.pojo.po.LogPo;
import com.example.springsecuritydemo3.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LoginLogoutAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private LogService logService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");

    @Pointcut("execution(* com.example.springsecuritydemo3.controller.UserController.login(..))")
    private void loginPointcut() {}

    @Pointcut("execution(* com.example.springsecuritydemo3.controller.UserController.logout(..))")
    private void logoutPointcut() {}

    @Around("loginPointcut()")
    public Object logLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        Date date = new Date();
        Object result = joinPoint.proceed();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LogPo log = new LogPo(user.getUsername(), "login", simpleDateFormat.format(date.getTime()));
        logService.save(log);
        return result;
    }

    @Around("logoutPointcut()")
    public Object logLogout(ProceedingJoinPoint joinPoint) throws Throwable {
        Date date = new Date();
        Object result = joinPoint.proceed();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        LogPo log = new LogPo(request.getParameter("username"), "logout", simpleDateFormat.format(date.getTime()));
        logService.save(log);
        return result;
    }


}
