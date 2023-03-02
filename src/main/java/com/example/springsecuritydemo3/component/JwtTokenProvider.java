package com.example.springsecuritydemo3.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String SIGN = "demo";

    //生成Token
    //header.payload.sign
    public String generateToken(UserDetails userDetails) {
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("username", userDetails.getUsername());
        builder.withClaim("createtime", new Date());

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 3); //默认7天过期
        String token = builder.withExpiresAt(instance.getTime())       //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));         //sign
        return token;
    }

    public String logout(UserDetails userDetails){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 1);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("username", userDetails.getUsername());
        builder.withClaim("createtime", new Date());


        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    // 判断token是否过期
    public boolean isTokenExpired (String token){
        try {
            JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    // 在token中获取用户名
    public String getUserNameFromToken(String jwt) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(jwt);
        String username = decodedJWT.getClaim("username").asString();
        return username;
    }
}

