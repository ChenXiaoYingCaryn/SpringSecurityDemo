package com.example.springsecuritydemo3;

import com.example.springsecuritydemo3.component.JwtTokenProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xiaoying
 * @create 2023/2/25
 */

public class test {


    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    JwtTokenProvider jwtTokenProvider;

    @Test
    public void tokenTest(){
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
//        Role role = new Role(1,"ROLE_admin");
//        HashSet<Role> auths = new HashSet<>();
//        auths.add(role);
//        User user = new User(1, "test", "$2a$10$t0K.hvBNAWoO6sgL9.V.WuHs/3x/mPbKjjv8SzitJPcx1urFAFS02", auths);
//        UserDetails userDetails = userService.loadUserByUsername("test");
//        String name = jwtTokenProvider.getUserNameFromToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVhdGV0aW1lIjoxNjc3MzA2NjQ3LCJleHAiOjE2Nzc1NjU4NDcsInVzZXJuYW1lIjoidGVzdCJ9._sSJRV4Vtla0e9Xs_WMF647j1wOEl_YWF4YMU4ipmtY");
//        System.out.println(name);
        String code = passwordEncoder.encode("user01");
        System.out.println(code);
    }
}
