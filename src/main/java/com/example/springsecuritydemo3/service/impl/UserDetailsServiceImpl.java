package com.example.springsecuritydemo3.service.impl;

import com.example.springsecuritydemo3.dao.UserDao;
import com.example.springsecuritydemo3.factory.UserFactory;
import com.example.springsecuritydemo3.pojo.vo.UserVo;
import com.example.springsecuritydemo3.pojo.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserPo> list = userDao.getUserByUsername(username);
        if(list.isEmpty()){
            throw new UsernameNotFoundException("用户名不存在");
        }
        UserVo user = UserFactory.UserPoToUser(list);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
