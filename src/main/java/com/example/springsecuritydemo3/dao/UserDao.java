package com.example.springsecuritydemo3.dao;

import com.example.springsecuritydemo3.pojo.po.UserPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("SELECT phone FROM db_user WHERE use_rname = #{username}")
    String findPhoneByUsername(String username);

    @Update("UPDATE db_user_status SET token = #{token} where user_name = #{username}")
    void updateUserStatus(String token, String username);

    @Insert("INSERT INTO db_user_status (user_name) VALUES (#{username})")
    void createUserStatus(String username);

    @Select("SELECT token FROM db_user_status where user_name = #{username}")
    String getTokenByUsername(String username);

    @Select("SELECT u.id as user_id, user_name, u.password, u.phone, r.id as role_id, r.name as role_name " +
            "FROM db_user u " +
            "INNER JOIN db_user_role ur ON u.id = ur.user_id " +
            "INNER JOIN db_role r ON ur.role_id = r.id " +
            "where u.user_name = #{username}")
    List<UserPo> getUserByUsername(String username);

    @Insert("INSERT INTO db_user (user_name, password, phone) VALUES " +
            "(#{userPo.userName}, #{userPo.password}, #{userPo.phone})")
    void register(@Param("userPo") UserPo userPo);

}
