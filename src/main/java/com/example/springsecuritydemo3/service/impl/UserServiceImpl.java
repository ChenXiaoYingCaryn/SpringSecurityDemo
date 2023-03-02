package com.example.springsecuritydemo3.service.impl;

import com.example.springsecuritydemo3.common.CommonResult;
import com.example.springsecuritydemo3.component.JwtTokenProvider;
import com.example.springsecuritydemo3.dao.RoleDao;
import com.example.springsecuritydemo3.dao.UserDao;
import com.example.springsecuritydemo3.factory.UserFactory;
import com.example.springsecuritydemo3.pojo.dto.UserDto;
import com.example.springsecuritydemo3.pojo.dto.UserRegisterDto;
import com.example.springsecuritydemo3.pojo.po.UserPo;
import com.example.springsecuritydemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CommonResult getPassword(String password) {
        return CommonResult.success(passwordEncoder.encode(password));
    }

    @Override
    public CommonResult findPhoneByUsername(String username) {
        String phone = "";
        try{
            phone = userDao.findPhoneByUsername(username);
        }catch (Exception e){
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(phone);
    }

    @Override
    public CommonResult logout(String username) {
        String token = "";
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            token = jwtTokenProvider.logout(userDetails);
            userDao.updateUserStatus(token, username);
        } catch (AuthenticationException e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success("logout success");
    }

    @Override
    public CommonResult login(UserDto userDto) {
        String token = "";
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
            if (!passwordEncoder.matches(userDto.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenProvider.generateToken(userDetails);
            userDao.updateUserStatus(token, userDetails.getUsername());
        } catch (AuthenticationException e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(token);
    }

    @Transactional
    @Override
    public CommonResult register(UserRegisterDto userRegisterDto) {
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        UserPo userPo = UserFactory.UserRegisterDtoToUserPo(userRegisterDto, 2L);
        try{
            userDao.register(userPo);
            userDao.createUserStatus(userPo.getUserName());
            roleDao.bindingRoleForTheUser(userPo);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return CommonResult.success("register success");
    }
}
