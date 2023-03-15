package com.example.springsecuritydemo3.factory;

import com.example.springsecuritydemo3.pojo.dto.UserRegisterDto;
import com.example.springsecuritydemo3.pojo.vo.RoleVo;
import com.example.springsecuritydemo3.pojo.vo.UserVo;
import com.example.springsecuritydemo3.pojo.po.UserPo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserFactory {

    public static UserVo UserPoToUser(List<UserPo> list){
        Set<RoleVo> roles = new HashSet<>();
        for(UserPo item : list){
            roles.add(new RoleVo(item.getRoleId(), item.getRoleName()));
        }

        UserPo userPo = list.get(0);
        UserVo user = new UserVo(
                userPo.getUserId(),
                userPo.getUserName(),
                userPo.getPassword(),
                userPo.getCountryCode(),
                userPo.getPhone(),
                roles);
        return user;
    }

    public static UserPo UserRegisterDtoToUserPo(UserRegisterDto userRegisterDto, Long roleId){
        UserPo userPo = new UserPo(
                userRegisterDto.getUserName(),
                userRegisterDto.getPassword(),
                userRegisterDto.getCountryCode(),
                userRegisterDto.getPhone(),
                roleId
        );
        return userPo;
    }


}
