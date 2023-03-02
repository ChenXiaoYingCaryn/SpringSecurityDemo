package com.example.springsecuritydemo3.dao;

import com.example.springsecuritydemo3.pojo.po.UserPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleDao {

    @Insert("INSERT INTO db_user_role (user_id, role_id) " +
            "SELECT id, #{userPo.roleId} " +
            "FROM db_user " +
            "WHERE user_name = #{userPo.userName}")
    void bindingRoleForTheUser(@Param("userPo") UserPo userPo);
}
