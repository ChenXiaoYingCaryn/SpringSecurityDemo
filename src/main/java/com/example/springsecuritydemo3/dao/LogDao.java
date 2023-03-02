package com.example.springsecuritydemo3.dao;

import com.example.springsecuritydemo3.pojo.po.LogPo;
import com.example.springsecuritydemo3.pojo.vo.LogVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface LogDao {

    @Select("SELECT * FROM db_log WHERE user_name = #{username}")
    List<LogVo> queryUserOperationLogByUsername(String username);

    @Select("SELECT * FROM db_log")
    List<LogVo> queryUserOperationLog();

    @Insert("INSERT INTO db_log (user_name, operation, create_time) VALUES (#{logPo.userName}, #{logPo.operation}, #{logPo.createTime})")
    void save(@Param("logPo") LogPo logPo);

}
